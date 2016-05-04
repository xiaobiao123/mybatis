package socket;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.ByteArrayInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.PrintWriter;  
import java.io.StringReader;  
import java.net.Socket;  
import java.net.UnknownHostException;  
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
  

import org.apache.commons.lang.StringUtils;  
import org.apache.log4j.Logger;  
import org.dom4j.Document;  
import org.dom4j.DocumentException;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.Node;  
import org.dom4j.io.SAXReader;  
  
  
/** 
 * 请求的XML：<?xml version="1.0" encoding="gb2312" ?> 
 *           <request type="add/delete/query">     
 *              <resource class="if" resid="172.19.128.12 FastEthernet3/7" ne="172.19.128.12"/> 
 *           </request> 
 * 送回的XML：<?xml version="1.0" encoding="gb2312" ?> 
 *          <respond type ="add/delete/query">     
 *          <resource class="if" resid="172.19.128.12 FastEthernet3/7" ne="172.19.128.12"/> 
 *          <result value="0/-1/-2/-21/-22/-23"/> 
 *          </respond> 
 *  
 * */  
public class SocketClient extends Thread {  
  
    private static final Logger logger = Logger.getLogger(SocketClient.class);  
      
    /** 
     * 生成请求XML文档 
     * */  
    private Document createXML(){  
        Document document = DocumentHelper.createDocument();  
        Element requestElement = document.addElement("request");  
          
        //加一行注释  
//      requestElement.addComment("请求xml格式的信息");  
          
       // requestElement.addAttribute("");  
          
        Element resourceElement = requestElement.addElement("resource");  
        resourceElement.addAttribute("class", "if");  
        resourceElement.addAttribute("resid", "'172.19.128.12 FastEthernet3/7");  
        resourceElement.addAttribute("ne", "172.19.128.12");  
          
        return document;  
    }  
      
    /** 
     * 修改XML文档 
     * */  
    private String updateXML(String xmlString){  
        String xml = "";  
        SAXReader reader = new SAXReader();  
        try {  
            Document doc = reader.read( new StringReader(xmlString.trim()));  
            List list = doc.selectNodes("/request");  
            Iterator it = list.iterator();  
            while(it.hasNext()){  
                Element requestElement = (Element)it.next();  
                requestElement.setName("respond");   //修改根节点名字  
                Element resultElement = requestElement.addElement("result");   //增加一个子节点  
                resultElement.addAttribute("value", "-22");  
            }  
              
            xml = doc.asXML();  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        return xml;  
    }  
      
    private List<ClientDto> parseXML(String xml){  
        List<ClientDto> list = new ArrayList<ClientDto>();  
        ClientDto cdDto = new ClientDto();  
        SAXReader saxReader = new SAXReader();     
        Document document = null;     
        try {     
//          document = DocumentHelper.parseText(xml);  
            document = saxReader.read(new ByteArrayInputStream(xml.getBytes()));     
              
            Iterator dociter = document.nodeIterator();  
            for (Iterator iterator = dociter; iterator.hasNext();) {  
                Node node = (Node) iterator.next();  
                Element nodeInfo = node.getParent();  
                String nodeName = nodeInfo.getName();  
                if(nodeName.equals("request")){  
                    cdDto.setRootType(nodeInfo.attributeValue("type"));  
                }  
                if(nodeName.equals("resource")){  
                    cdDto.setClassType(nodeInfo.attributeValue("class"));  
                    cdDto.setResid(nodeInfo.attributeValue("resid"));  
                    cdDto.setNe(nodeInfo.attributeValue("ne"));  
                }  
                list.add(cdDto);  
            }  
        } catch (DocumentException e) {       
            e.printStackTrace();     
        }    
          
        return list;  
    }  
      
    /** 
     * 发送请求XML格式的信息 
     * */  
    private void sendXML(Socket socket_client, Document document){  
        OutputStream os = null;  
        try {  
            os = new BufferedOutputStream(socket_client.getOutputStream());  
            String xmlStr = document.asXML().trim();  
            byte[] information = getInformation(xmlStr);  
            os.write(information);  
            os.flush();  
            os.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 连接telnet后输入的验证字符串 
     * */  
    private void connectedStr(Socket socket_client, String inStr){  
        PrintWriter pw = null;  
        try {  
            pw = new PrintWriter(socket_client.getOutputStream());  
//          pw.println(inStr);  
            pw.write(inStr);  
            pw.flush();  
            pw.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    /** 
     * 将int型的数据类型转换成byte[]类型 
     *  
     * @param n 
     * @return 
     */  
    private static byte[] getIntToByte(int n) {  
        byte[] V_byte = new byte[4];  
        V_byte[0] = (byte) (n & 0xff);  
        V_byte[1] = (byte) (n >> 8 & 0xff);  
        V_byte[2] = (byte) (n >> 16 & 0xff);  
        V_byte[3] = (byte) (n >> 24 & 0xff);  
        return V_byte;  
    }  
    public static int getByteToInt(byte[] b){  
       int result = b[3] & 0xff;  
       result = (result << 8) + (b[2] & 0xff);  
       result = (result << 8) + (b[1] & 0xff);  
       result = (result << 8) + (b[0] & 0xff);  
       return result;  
      
    }  
      
    /** 
     * 将String类型的XML转换成byte数组 
     * */  
    private byte[] getInformation(String xml){  
        byte[] alarm = xml.getBytes();  
        byte[] length = getIntToByte(alarm.length);  
        int infoLength = 4 + alarm.length;  
        byte[] information = new byte[infoLength];  
        for (int i = 0; i < information.length; i++) {  
            if (i < 4) {  
                information[i] = length[i];  
            } else {  
                information[i] = alarm[i - 4];  
            }  
        }  
        return information;  
    }  
      
    /** 
     * 读取由Server端送回来的XML数据 
     * */  
    private String receiveInfo(Socket socket_client){  
        InputStream is = null;  
        String xml = "";  
        try {  
            is = new BufferedInputStream(socket_client.getInputStream());  
            byte [] head = new byte[4];  
            is.read(head ,0,4);  
            int bodyLength = getByteToInt(head);  
            if(bodyLength == 0) return null;  
            byte[] body = new byte[1024];  
            is.read(body, 0, bodyLength);  
            xml = new String(body, 0, bodyLength).trim();  
            logger.info("#### 请求XML = "+xml);  
            if(xml.charAt( xml.length()-1)!='>'){  
             logger.debug("#### 错误格式的xml文件");  
                new IOException("xml文件的最后的字符不是 '>' ");  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        return xml;  
    }  
      
    /** 
     * @param args 
     */  
    @SuppressWarnings("unused")
    public static void main(String[] args) {  
        SocketClient sc = new SocketClient();  
        Socket socket = null;  
        
        Document document1 = sc.createXML();   //生成XML文档  
        String inStr = "";   //输入验证字符串内容  
        if(args.length >0){  
            inStr = StringUtils.trim(args[0]);  
        }else{  
            inStr = "start";  
        }  
        try {  
            socket = new Socket("127.0.0.1", 4700);   //Telnet连到到远程服务端  
            sc.connectedStr(socket, inStr);   //输入验证字符串  
            Document document = sc.createXML();   //生成XML文档  
            sc.sendXML(socket, document);  //发送XML文档  
            Thread.sleep(30 * 60 * 1000);   //30分钟  
            String resultXML = sc.receiveInfo(socket);  //接收XML文档  
            String updatedXML = sc.updateXML(resultXML);  
              
              
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
          
    }  
  
}  
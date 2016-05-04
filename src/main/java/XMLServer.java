
/*
 * Created on 2004-11-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.*;
import java.net.*;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;


/**
 * @author Shaird
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XMLServer {    
    public static void main(String[] args) throws IOException,SAXException{
    String URL="../shaird/xmldata/book.xml";    
    //通过指定解析器的名称来动态加载解析器
    XMLReader reader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

    //处理内容前要注册内容管理器
   // reader.setContentHandler(new XMLHandler());
    
    ServerSocket server=new ServerSocket(8210);
    Socket client=server.accept();
    BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(URL,false)));
    String str=null;
    String xml=new String();
    while((str = br.readLine()) != null){
        xml+=str+"/n";
        out.println(str);
    } 
    out.flush();
    System.out.println(xml);

    InputSource inputSource= new InputSource(xml);
    inputSource.setSystemId(URL);
    reader.parse(inputSource);
    client.close();
    
    File f = new File(URL);
    f.delete();

    } 

}
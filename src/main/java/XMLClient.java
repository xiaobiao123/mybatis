/*
 * Created on 2004-11-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.net.*;
import java.io.*;

/**
 * @author Shaird
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XMLClient {
    static Socket sock;

    public static void main(String[] args)throws Exception{
    sock = new Socket(InetAddress.getLocalHost(),8210);
 
    PrintWriter write = new PrintWriter(new OutputStreamWriter (sock.getOutputStream()),true); 
    File file = new File("../shaird/xmldata/peopel.xml");  //用于发送的文件路径
    BufferedReader read = new BufferedReader(new FileReader(file)); 

    String str=null;
    while((str = read.readLine()) != null) 
    write.println(str); 
    write.flush(); 
    sock.close();
    }

}

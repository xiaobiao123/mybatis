package cstest;  
  
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
  
public class Server {  
 ServerSocket ss ;  
 Socket serverSocket;  
 InputStream inStream;  
 OutputStream outStream;  
   
 public Server(){  
  try {  
	  
   System.out.println("====================Server==================");  
   ss = new ServerSocket(8090);  
   serverSocket= ss.accept();  
   System.out.println("--------------some guest connected----------------"); 
   //重客户端读取信息
   inStream = serverSocket.getInputStream();  
   outStream = serverSocket.getOutputStream();  
  } catch (Exception e) {  
   e.printStackTrace();  
  }  
 }  
   
 public void conn(){  
  new Thread(){  
   public void run() {  
	   System.out.println(System.currentTimeMillis());
    	  Reader reader = new InputStreamReader(inStream);  
    	  char chars[] = new char[64];  
          int len;  
          StringBuilder sb = new StringBuilder();  
          String temp;  
          int index;  
          try {
	        while ((len=reader.read(chars)) != -1) {  
	             temp = new String(chars, 0, len);  
	             if ((index = temp.indexOf("eof")) != -1) {//遇到eof时就结束接收  
	                sb.append(temp.substring(0, index));  
	                break;  
	             }  
	             sb.append(temp);  
	          }
        } catch (IOException e) {
	        e.printStackTrace();
        }  
          System.out.println("from client: " + sb); 
          //读完后写一句  
          Writer writer = new OutputStreamWriter(outStream);  
          try {
	        writer.write("Hello Client.");  
	        writer.write("eof");  
	        writer.flush();  
	        writer.close();  
	        reader.close();
        } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }  
   }  
  }.start();  
 }  
   
 public static void main(String args[]){  
  Server server = new Server();  
  server.conn();  
 }  
}  
  
   
  

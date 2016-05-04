package cstest;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket socket;
	InputStream inStream;
	OutputStream outStream;

	public Client() {
		try {
			System.out.println("-------------This is client-----------------");
			socket = new Socket("127.0.0.1", 8090);
			inStream = socket.getInputStream();
			outStream = socket.getOutputStream();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void conn() {
		new Thread() {
			public void run(){
				  Writer writer = new OutputStreamWriter(outStream);  
			      try {
	                writer.write("Hello Server.");
	                writer.write("eof");
	                writer.flush();
                } catch (IOException e2) {
	                // TODO Auto-generated catch block
	                e2.printStackTrace();
                }  
			     
			     
			      //写完以后进行读操作  
			     Reader reader = new InputStreamReader(inStream);  
			      char chars[] = new char[64];  
			      int len;  
			      StringBuffer sb = new StringBuffer();  
			      String temp;  
			      int index;  
			      try {
	                while ((len=reader.read(chars)) != -1) {  
	                     temp = new String(chars, 0, len);  
	                     if ((index = temp.indexOf("eof")) != -1) {  
	                        sb.append(temp.substring(0, index));  
	                        break;  
	                     }  
	                     sb.append(new String(chars, 0, len));  
	                  }
	                
	                writer.close();
	                reader.close();
                } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }  
			      System.out.println("from server: " + sb);  
			}
		}.start();

	}

	public static void main(String[] args) {
		Client client = new Client();
		client.conn();
    }
}

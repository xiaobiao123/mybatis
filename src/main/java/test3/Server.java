package test3;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.OutputStream;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class Server {  
    public static void main(String args[]) throws IOException {  
        String reces = null;  
        int len;  
        Socket socket = null;  
        OutputStream outputstream = null;  
        InputStream inputstream = null;  
        byte[] rece = new byte[1000];  
        try {  
            ServerSocket serversocket = new ServerSocket(9527); // 服务器的套接字，端口为9527  
            while (true) {  
                socket = serversocket.accept();  
                inputstream = socket.getInputStream();// 得到输入流  
                outputstream = socket.getOutputStream();// 得到输出流  
                len = inputstream.read(rece);// 接受客户端消息  
                if (len != 0)  
                    reces = new String(rece, 0, len);  
                System.out.println(reces);  
                BufferedReader bufferreader = new BufferedReader(  
                        new InputStreamReader(System.in));  
                outputstream.write(("服务器....."+bufferreader.readLine()).getBytes());// 返回给客户端的欢迎信息  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            inputstream.close();  
            outputstream.close();  
            socket.close();// 记住一定要关闭这些输入，输出流和套接字  
        }  
  
    }  
}  
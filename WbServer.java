import java.io.*;
import java.net.*;
import java.util.*;
//change1
public class WbServer {
    public static void main(String[] args)throws Exception {
		ServerSocket ss;
        Thread t;
        Socket sc;
        try
        {
        ss=new ServerSocket(9999);
        System.out.println("Server is Listening at" + InetAddress.getLocalHost().getHostAddress()+"at port"+ss.getLocalPort());
        
        while(true)
        {
            sc=ss.accept();
            
            t=new WbThread(sc);   
        }
        }
        catch(Exception e)
		{
		System.out.println(e);
		}
    }
    
}

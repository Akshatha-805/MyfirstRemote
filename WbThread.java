import java.io.*;
import java.net.*;
import java.util.*;
//change1
public class WbThread extends Thread
{
    final static String CRLF="\r\n";

    Socket s;
     
    public WbThread(Socket s)throws Exception
    {
        this.s=s;
        start();
    }
    
    public void run()
    {
        try
        {

           DataInputStream is=new DataInputStream(s.getInputStream());
            DataOutputStream os=new DataOutputStream(s.getOutputStream());
 
  String input = is.readUTF();
            System.out.println();
          StringTokenizer tokens=new StringTokenizer(input);
            tokens.nextToken();
           String fname=tokens.nextToken();
           fname="."+fname;
  File file=new File(fname);

           FileInputStream fis=null;
            boolean fileExists=true;
            try
            {
                fis=new FileInputStream(fname);
                
            }
            catch(FileNotFoundException e)
            {
               fileExists=false; 
            }
            String statusLine=null;
            if(fileExists)
            {
            
                statusLine="HTTP/1.0 200 OK"+CRLF;
     		}
            else
            {
                statusLine="HTTP/1.0 404 NOT FOUND"+CRLF;
            }
if(fileExists)
			{
				os.writeBoolean(true);//from proxy2
				FileInputStream fis2=new FileInputStream(fname);
					int ch;
					while((ch=fis2.read())!=-1)
					{
						os.write(ch);
					}
					//fis.close();
					os.close();
					

           			
			}
           

            if(fileExists)
            {
             byte[] buffer=new byte[1024];
                    int bytes=0;
                    while((bytes=fis.read(buffer))!=-1)
                    {
                        os.write(buffer,0,bytes);
                    }
       
                is.close();
            }
            else
            {
file = new File("./404.html");
FileInputStream fis2=new FileInputStream(file);
byte[] buffer=new byte[1024];
                    int bytes=0;
                    while((bytes=fis2.read(buffer))!=-1)
                    {
                        os.write(buffer,0,bytes);
                    }
                os.writeBytes(statusLine);
  
            }


             os.close();
            
             s.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
          
        }
    }
 }
        
    

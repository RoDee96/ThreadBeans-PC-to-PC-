import java.net.*;
import java.io.*;
public class Streams{
    static Socket s;
    static InputStream is;
    static OutputStream os;    
    
    public static void stream(){
        try{
            is = s.getInputStream();
            os = s.getOutputStream();
            
            if(Server.status == true){
           
                InetAddress inet = InetAddress.getLocalHost();
                String domain[] = inet.toString().split("/");
                int num = domain[0].length();
                os.write(num);
                os.flush();
                
                byte b[] = new byte[num];
                b = domain[0].getBytes();
                os.write(b);
                os.flush();
                
                num=is.read();
                
                byte a[]= new byte[num];
                is.read(a);
                System.out.println("Server Stream!");
                MainFrame.HeaderLabel.setText("Connected to "+new String(a));
                MainFrame.ConnectedPCName = new String(a);
                
                //ip receiver...
                num = is.read();
                byte w[] = new byte[num];
                is.read(w);
                MainFrame.ConnectedIP = new String(w);
                System.out.println(MainFrame.ConnectedIP+" is Client IP!");
            }
            else if(Client.status == true){
                //createOClient();
                int num=is.read();
                
                byte b[]= new byte[num];
                is.read(b);
                System.out.println("Client Stream!");
                MainFrame.HeaderLabel.setText("Connected to "+new String(b));
                MainFrame.ConnectedPCName = new String(b);
                
                InetAddress inet = InetAddress.getLocalHost();
                String domain[] = inet.toString().split("/");
                num = domain[0].length();
                os.write(num);
                os.flush();
                
                byte a[] = new byte[num];
                a = domain[0].getBytes();
                os.write(a);
                os.flush();
                
                //ip sending...
                num = domain[1].length();
                os.write(num);
                os.flush();
                byte w[] = new byte[num];
                w = domain[1].getBytes();
                os.write(w);
                os.flush();
            }
            
            //new Receiver();
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error in setting Streams for 3000!");
        }
        
        new Receiver();
        
    }
    
    
}

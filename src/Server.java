import java.net.*;

public class Server {
    static ServerSocket ss;
    static Socket s;
    static Boolean status=false;
    
    public static void firstConnection(){
        String ConnectedIP=null;
        try{
            System.out.println("Waiting: At 3000.");
            ss = new ServerSocket(3000);
            s = ss.accept();
            status = true;
            
            Streams.s = s;
            
        }catch(Exception e){
            System.out.println("Connection Failed due to cancellation.");
        }
        
    }
    
}

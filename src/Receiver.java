import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver {
    InputStream is = Streams.is;
    public Receiver(){
        Thread com = new Thread(){
            public void run(){
                while(Server.status == true || Client.status == true){
                    System.out.println("Receiver started at 3000!");
                    byte read[] = new byte[10];
                    
                    try{
                        is.read(read);
                        System.out.println("\""+new String(read)+"\" received");
                    }catch(Exception e){
                        System.out.println("Cannot read from 3000 port's Stream!");
                    }
                    
                    String r = new String(read).trim();
                    if(r.equals("CS")){
                        SendFile.AbortTransfer = true;
                        try {
                            SendFile.server.close();
                            SendFile.ss.close();
                            SendFile.stoploop = true;
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(r.equals("CR")){
                        ReceiveFile.AbortTransfer = true;
                        try {
                            ReceiveFile.receive.close();
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(r.equals("sending")){
                       ReceiveFile.receive();
                    }
                    if(r.equals("destroy")){
                        reset();
                        try {
                            Server.ss.close();
                            Streams.s.close();
                            
                            
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        
                        break;
                    }
                    if(r.equals("Exit")){
                        try{
                            Server.ss.close();
                            Streams.s.close();
                            System.out.println("Socket closed at 3000!");
                            //new RestoreIPs();
                            System.out.println("All streams closed, GoodBye!");
                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }finally{
                            new RestoreIPs();
                            System.exit(0);
                        }
                    }
                    
                    
                }
            }
        };com.start();
    }
    
    public static void reset(){
            
        Client.status = false;
        Server.status = false;
        
        MainFrame.HeaderLabel.setText("Click to connect!");
        MainFrame.StatusLabel.setText("Connection Lost!");
        
        MainFrame.CreateNButton.setVisible(true);
        MainFrame.JoinNButton.setVisible(true);
        MainFrame.SendButton.setVisible(false);
        MainFrame.SettingsButton.setVisible(true);
        
        MainFrame.CancelJNButton.setVisible(false);
        MainFrame.CancelCNButton.setVisible(false);
        
        MainFrame.SettingsButton.setEnabled(false);
        MainFrame.CreateNButton.setEnabled(false);
        MainFrame.JoinNButton.setEnabled(false);
         
        MainFrame.ProgressBar.setValue(0);
          
        try{
            Streams.s.close();
            System.out.println("Client is now Clodes!");
        }catch(Exception e){
            System.out.println("Client Not Closed!");
        }
           
        Thread wait = new Thread(){
            public void run(){
                try{
                    Thread.sleep(3500);
                    MainFrame.CreateNButton.setEnabled(true);
                    MainFrame.JoinNButton.setEnabled(true);
                    MainFrame.SettingsButton.setEnabled(true);
                    MainFrame.StatusLabel.setText("Click a Button To Connect!");
                }catch(Exception e){
                    System.out.println("Disabling did not work!");
                }
            }
        };wait.start();
            
    }
    
}


import java.io.*;
import javax.swing.*;
import java.net.*;
public class ReceiveFile {
    static String storageLocation = MainFrame.storageLocation;
    static boolean AbortTransfer;
    static InputStream is;
    static OutputStream os;
    static Socket receive;
    static FileOutputStream fos;
    public static void receive(){
        try{
            System.out.println(MainFrame.ConnectedIP);
            receive = new Socket(MainFrame.ConnectedIP,4000);
            receive.setReuseAddress(true);
            is = receive.getInputStream();
            os = receive.getOutputStream();
            
            int fvsize = is.read();
            byte filesize[] = new byte[fvsize];
            is.read(filesize);
            System.out.println("Filesize is: " + new String(filesize));
            
            int namesize=is.read();
            System.out.println("Filename size is: "+namesize);
            byte filename[] = new byte[(byte)namesize];
            
            is.read(filename);
            String str = new String(filename);
            System.out.println("Filename is:" + str);
            fos = new FileOutputStream(storageLocation+str);
            
            
            //for progress bar
            double size = Double.parseDouble(new String(filesize))/1024;
            double i=1;
            //end
            
            MainFrame.FileNameLabel.setText("Receiving file: "+new String(filename));
            MainFrame.CreateNButton.setEnabled(false);
            MainFrame.JoinNButton.setEnabled(false);
            MainFrame.SendButton.setText("Cancel Receiving");
            //MainFrame.SendButton.setEnabled(false);
            
            MainFrame.CancelCNButton.setEnabled(false);
            MainFrame.CancelJNButton.setEnabled(false);
            MainFrame.exitButton.setEnabled(false);
            
            AbortTransfer = false;
            byte[] bytes = new byte[1024];
            int count;
            while(true){
                if(AbortTransfer == true)
                    break;
                
                int percent =(int) i*100/(int)size;
                MainFrame.ProgressBar.setValue(percent);
                MainFrame.PercentLabel.setText(percent +"%   completed!");
                i++;
                
                if((count = is.read(bytes)) > 0){
                    fos.write(bytes, 0, count);
                    fos.flush();
                }
                else
                    break;
           
            }
            
            if(AbortTransfer == false){//sucessfull receving
                try{
                    System.out.println("File received Successfully!");
                    MainFrame.PercentLabel.setText("100% completed!");
                    Thread.sleep(1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            else if(AbortTransfer == true){//unsucessful receving
                try{
                    System.out.println("Error Receiving file!");
                    MainFrame.PercentLabel.setText("Error receeiving file!");
                    MainFrame.StatusLabel.setText("Aborted Transfer!");
                    
                    Thread.sleep(3000);
                    MainFrame.StatusLabel.setText(" ");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
                    
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("File not received!");
        }
        finally{
            try{
                MainFrame.CancelCNButton.setEnabled(true);
                MainFrame.CancelJNButton.setEnabled(true);
                MainFrame.exitButton.setEnabled(true);
                
                MainFrame.CreateNButton.setEnabled(true);
                MainFrame.JoinNButton.setEnabled(true);
                MainFrame.SendButton.setEnabled(true);
                MainFrame.SendButton.setText("Send");
            
                MainFrame.PercentLabel.setText(" ");
                MainFrame.FileNameLabel.setText(" ");
                MainFrame.ProgressBar.setValue(0);
                is.close();
                os.close();
                receive.close();
                fos.close();
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("ReceiveFile Streams not closed!");
            }
        }
    }
}

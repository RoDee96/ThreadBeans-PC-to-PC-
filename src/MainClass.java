import java.io.File;
import java.io.IOException;
import java.net.Socket;
import static javax.swing.SwingUtilities.invokeLater;

public class MainClass {
    static StartFrame f1;
    
    public static void main(String args[])throws Exception{
        invokeLater(new Runnable(){
            public void run(){
                f1 = new StartFrame();
                try {
                    f1.start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(2000);

        //to make visible that network established!
        f1.pwp.setVisible(true);
        //setting the IP of PC...
        //SetIP_ToUse siptu = new SetIP_ToUse();
        
        new File("C:/ReceivedFiles").mkdir();
        
        Thread.sleep(2000);
        //First CheckPoint...
        f1.dispose();
        
        MainFrame.start();
        
    
    }
    
}

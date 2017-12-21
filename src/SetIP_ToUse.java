import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SetIP_ToUse {
    public SetIP_ToUse(){
        try{
            Runtime.getRuntime().exec("netsh interface ip set address name=\"Ethernet\" static 192.168.1.0");
            for(int i = 1; i<10; i++){
                Runtime.getRuntime().exec("netsh interface ip set address name=\"Ethernet"+" "+i+"\""+" static 192.168.1."+i);
            }
            
            Runtime.getRuntime().exec("netsh interface ip set address name=\"Wi-fi\" static 192.168.1.0 ");
            for(int i = 1; i<10; i++){
                Runtime.getRuntime().exec("netsh interface ip set address name=\"Wi-fi"+" "+i+"\""+" static 192.168.1."+i);
            }
           // System.out.println("IPs changed!");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
}        
public class RestoreIPs {
    public RestoreIPs(){
        try{
            //for Ethernet
            Runtime.getRuntime().exec("netsh interface ip set address name=\"Ethernet\" dhcp");
            for(int i = 1; i<10; i++){
                Runtime.getRuntime().exec("netsh interface ip set address name=\"Ethernet"+" "+i+"\""+" dhcp");
            }
            
            //for Wifi
            Runtime.getRuntime().exec("netsh interface ip set address name=\"Wi-fi\" dhcp");
            for(int i = 1; i<10; i++){
                Runtime.getRuntime().exec("netsh interface ip set address name=\"Wi-fi"+" "+i+"\""+" dhcp");
            }
            
            System.out.println("IPs restored to dhcp!");
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}

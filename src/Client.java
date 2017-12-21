
import java.net.*;
import javax.swing.JOptionPane;

public class Client {

    static Socket client;
    static boolean status;
//
//    public static void start() {
//        if (Settings.modIp == true) {
//            System.out.println("Connecting To modified IP!");
//            String IP = Settings.IP;
//            Thread StartClient = new ThreadOfClient(IP);
//            StartClient.start();
//            try {
//                Thread.sleep(100);
//                StartClient.stop();
//            } catch (Exception e) {
//
//                System.out.println("Client Thread not Started!");
//            }
//        } else {
//            for (int i = 0; i < 10; i++) {
//                if (Client.status == true) {
//                    break;
//                }
//                String IP = "192.168.1." + i;
//                Thread StartClient = new ThreadOfClient(IP);
//                StartClient.start();
//                try {
//                    Thread.sleep(300);
//                    StartClient.stop();
//
//                } catch (Exception e) {
//                    System.out.println("Client Thread not Started!");
//                }
//
//            }
//        }
//
//    }
//
//    public static class ThreadOfClient extends Thread {
//
//        String IP;
//
//        public ThreadOfClient(String IP) {
//            this.IP = IP;
//        }
//
//        public void run() {
//            try {
//                System.out.println("Trying: " + IP);
//                client = new Socket(IP, 3000);
//                //client.setSoTimeout(100);
//                Streams.s = client;
//
//                MainFrame.ConnectedIP = IP;
//                status = true;
//                System.out.println("Client: Connected " + IP + " at 3000.");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println("Client not Started at 3000 at" + IP + ".");
//            }
//        }
//
//    }

    public static void start(String str) {
        String IP = str;
        System.out.println("Connecting To entered IP!");
        System.out.println("Trying: " + IP);

        try {

            client = new Socket(IP, 3000);
            //client.setSoTimeout(100);
            Streams.s = client;

            MainFrame.ConnectedIP = IP;
            status = true;
            System.out.println("Client: Connected " + IP + " at 3000.");

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Client not Started at 3000 at" + IP + ".");
        }
    }

}

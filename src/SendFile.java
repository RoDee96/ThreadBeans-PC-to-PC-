
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.*;
import java.net.*;

public class SendFile {

    static boolean AbortTransfer;
    static boolean stoploop = false;
    static InputStream is = null;
    static OutputStream os = null;
    static JFileChooser jfc;

    static ServerSocket ss;
    static Socket server;
    static FileInputStream fis;

    public static void Fend() {
        stoploop = false;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(true);

        jfc.showOpenDialog(null);

        File file1[];
        if ((file1 = jfc.getSelectedFiles()) != null) {
            int n = file1.length;
            if (file1.length == 0) {
                MainFrame.SendButton.setEnabled(true);
            }
            System.out.println(n + " files selected.");
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    if (stoploop == true) {
                        break;
                    }
                    nowSend(file1[i]);
                }
            }
        }

    }

    public static void nowSend(File file1) {
        try {
            byte msg[] = "sending".getBytes();
            try {
                Streams.os.write(msg);
                Streams.os.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ss = new ServerSocket(4000);
            ss.setReuseAddress(true);
            server = ss.accept();
            server.setReuseAddress(true);

            is = server.getInputStream();
            os = server.getOutputStream();

            String path = file1.getAbsolutePath();
            String filename = file1.getName();

            File file = new File(path);
            fis = new FileInputStream(file);

            int namesize = filename.length();
            byte name[] = filename.getBytes();
            String filesize = Long.toString((long) file.length());
            byte fsize[] = filesize.getBytes();
            System.out.println("Filesize is: " + new String(fsize));
            os.write(fsize.length);
            os.flush();
            os.write(fsize);
            os.flush();

            os.write(namesize);
            os.flush();
            System.out.println("Filename is: " + new String(name));
            os.write(name);
            os.flush();
            System.out.println("Sending...");

            //for progress bar
            double size = file.length() / 1024;
            double i = 1;
            //end 
            MainFrame.FileNameLabel.setText("Sending File: " + new String(name) + "...");
            MainFrame.CreateNButton.setEnabled(false);
            MainFrame.JoinNButton.setEnabled(false);
            MainFrame.SendButton.setText("Cancel Sending");

            MainFrame.CancelCNButton.setEnabled(false);
            MainFrame.CancelJNButton.setEnabled(false);
            MainFrame.exitButton.setEnabled(false);

            AbortTransfer = false;
            int count;
            byte bytes[] = new byte[1024];
            while (true) {
                if (AbortTransfer == true) {
                    //MainFrame.SendButton.setEnabled(false);
                    break;
                }

                if ((count = fis.read(bytes)) > 0) {
                    os.write(bytes, 0, count);
                    os.flush();
                } else {
                    break;
                }

                int percent = (int) (i * 100 / size);
                MainFrame.ProgressBar.setValue(percent);
                MainFrame.PercentLabel.setText(percent + "%   completed!");
                i++;

            }

            if (AbortTransfer == true) {
                try {
                    MainFrame.PercentLabel.setText("Sending Cancelled!");
                    Thread.sleep(3000);
                    MainFrame.SendButton.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    MainFrame.PercentLabel.setText("100% completed!");
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                os.write(-1);
                os.flush();
                System.out.println("File Transferred Successfully!");

            }

            MainFrame.CreateNButton.setEnabled(true);
            MainFrame.JoinNButton.setEnabled(true);
            //MainFrame.SendButton.setText("Send");
            MainFrame.SendButton.setEnabled(true);//for next version
            MainFrame.PercentLabel.setText(" ");
            MainFrame.FileNameLabel.setText(" ");
            MainFrame.ProgressBar.setValue(0);
            System.out.println("Write Finished!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Writing Failed!");
            MainFrame.SendButton.setEnabled(true);
            MainFrame.PercentLabel.setText(" ");
            MainFrame.FileNameLabel.setText(" ");
            MainFrame.ProgressBar.setValue(0);

        } finally {
            try {
                MainFrame.SendButton.setText("Send");
                MainFrame.CancelCNButton.setEnabled(true);
                MainFrame.CancelJNButton.setEnabled(true);
                MainFrame.exitButton.setEnabled(true);

                is.close();
                os.close();

                server.close();
                ss.close();
                fis.close();
            } catch (Exception e) {
                System.out.println("Sender streams or server not closed!");
                e.printStackTrace();
            }
        }
    }

}

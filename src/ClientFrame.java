
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ClientFrame extends JFrame {

    private JLabel backgroundImageLabel;
    private JTextField enterIPTextField;
    private JButton connectButton;
    private JButton cancelButton;
    private JProgressBar progressBar;
    private JLabel justLabel;
    private JLabel titleLabel;
    private JLabel hintLabel;
    private JLabel hintLabel2;

    JPanel mainPanel;
    static ClientFrame frame;

    Point initialClick;

    public ClientFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        getContentPane().setLayout(null);

        mainPanel = new JPanel();
        justLabel = new JLabel();
        enterIPTextField = new JTextField();
        progressBar = new JProgressBar();
        titleLabel = new JLabel();
        connectButton = new JButton();
        backgroundImageLabel = new JLabel();
        cancelButton = new JButton();
        hintLabel = new JLabel();
        hintLabel2 = new JLabel();

        mainPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(null);
        mainPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                initialClick = evt.getPoint();
            }
        });
        mainPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {

                // get location of Window
                int thisX = frame.getLocation().x;
                int thisY = frame.getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);

            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        justLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        justLabel.setText("Enter Server IP:");
        mainPanel.add(justLabel);
        justLabel.setBounds(20, 60, 150, 20);
        
        mainPanel.add(enterIPTextField);
        enterIPTextField.setFont(new java.awt.Font("Tahoma", 0, 20));
        enterIPTextField.setBounds(180, 60, 270, 30);
        
        hintLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
        hintLabel.setText("*Get it from the other PC from where you ");
        hintLabel.setBounds(180, 90, 280, 30);
        mainPanel.add(hintLabel);
        hintLabel2.setFont(new java.awt.Font("Tahoma", Font.BOLD, 12)); // NOI18N
        hintLabel2.setText("want to retrieve or send files.");
        hintLabel2.setBounds(185, 110, 280, 30);
        mainPanel.add(hintLabel2);
        
        mainPanel.add(progressBar);
        progressBar.setBounds(10, 180, 440, 15);

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titleLabel.setText("Connect to Server");
        mainPanel.add(titleLabel);
        titleLabel.setBounds(10, 10, 280, 40);

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });
        mainPanel.add(connectButton);
        connectButton.setBounds(10, 140, 210, 30);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        mainPanel.add(cancelButton);
        cancelButton.setBounds(240, 140, 210, 30);

        getContentPane().add(mainPanel);
        mainPanel.setBounds(0, 0, 470, 210);

        backgroundImageLabel.setIcon(new ImageIcon(getClass().getResource("/Lan.jpg"))); // NOI18N
        getContentPane().add(backgroundImageLabel);

        backgroundImageLabel.setBounds(0, 0, 470, 210);
        setSize(new Dimension(470, 210));
    }

    private void connectButtonActionPerformed(ActionEvent evt) {
        String str = enterIPTextField.getText();
        if (str.equals(null) || str.equals("") /*|| !MyRegex.isMatch(str)*/) {
            JOptionPane.showMessageDialog(null, "Invalid IP!", "Error", 0);
        } else {
            Thread Progress = new Thread() {
                public void run() {

                    for (int i = 0; i < 101; i++) {
                        progressBar.setValue(i);
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) {
                            System.out.println("ProgreeBar not Updated!");
                        }
                    }
                    progressBar.setValue(0);
                    //JoinNButton.setEnabled(true);
                    if (Client.status == false) {
                        progressBar.setValue(0);
                    }
                }
            };
            Progress.start();

            Thread StartClient = new Thread() {
                public void run() {
                    Client.start(enterIPTextField.getText()); //not for Thread...

                    if (Client.status == true) {
                        Streams.stream();
                        dispose();
                        MainFrame.CreateNButton.setVisible(false);
                        MainFrame.CancelJNButton.setVisible(true);
                        MainFrame.CancelJNButton.setEnabled(true);
                        MainFrame.SendButton.setBounds(445, 160, 140, 40);
                        MainFrame.SendButton.setVisible(true);
                    }

                    if (Client.status == false) {
                        MainFrame.StatusLabel.setText("Not Able To Connect!");
                        MainFrame.HeaderLabel.setText("Click To Connect!");

                        MainFrame.SettingsButton.setEnabled(true);
                        MainFrame.CreateNButton.setEnabled(true);
                        MainFrame.JoinNButton.setVisible(true);

                        MainFrame.CancelJNButton.setVisible(false);
                        MainFrame.CancelJNButton.setEnabled(true);
                        MainFrame.StatusLabel.setText(" ");
                    }
                }
            ;
            };StartClient.start();
        }

    }

    private void cancelButtonActionPerformed(ActionEvent evt) {

        MainFrame.SettingsButton.setEnabled(true);
        MainFrame.CreateNButton.setEnabled(true);
        MainFrame.JoinNButton.setVisible(true);

        MainFrame.JoinNButton.setEnabled(true);
        MainFrame.CancelJNButton.setVisible(false);
        MainFrame.CancelJNButton.setEnabled(true);
        MainFrame.StatusLabel.setText(" ");

        this.setVisible(false);
    }

    public void start(ClientFrame frame) throws Exception {
        this.frame = frame;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CLientConnectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CLientConnectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CLientConnectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CLientConnectFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame.setLocationRelativeTo(null);
                frame.setUndecorated(true);
                frame.setVisible(true);
            }
        });
    }

    public static void main(String args[]) throws Exception {
        ClientFrame frame = new ClientFrame();
        frame.start(frame);
    }

}

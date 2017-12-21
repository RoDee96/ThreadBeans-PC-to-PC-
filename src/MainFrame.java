
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.FontUIResource;

public class MainFrame extends JFrame {

    static String storageLocation = "C:/ReceivedFiles/";
    static MainFrame frame;

    Point initialClick;

    static Settings set = new Settings();
    static JButton exitButton;
    static JButton CreateNButton;
    static JButton JoinNButton;
    static JButton SendButton;
    static JButton SettingsButton;
    static JButton CancelCNButton;
    static JButton CancelJNButton;
    static JButton CancelSButton;
    static JButton CancelRButton;
    static JButton RefreshIPButton;

    static JLabel ImageLabel;
    static JLabel HeaderLabel;
    static JLabel StatusLabel;
    static JLabel FileNameLabel;
    static JLabel PercentLabel;
    static JLabel ipDisplayLabel;

    JPanel MainPanel;
    GUIPanel guiPanel;

    static JProgressBar ProgressBar;

    JOptionPane jop;

    DotPanels dot[] = new DotPanels[5];

    //for networking
    static String ConnectedPCName = null;
    static String ConnectedIP = null;

    public MainFrame() {

        //Variabe Initialization begins...
        MainPanel = new JPanel();
        guiPanel = new GUIPanel();

        HeaderLabel = new JLabel();
        StatusLabel = new JLabel();
        FileNameLabel = new JLabel();
        PercentLabel = new JLabel();
        ipDisplayLabel = new JLabel();

        SettingsButton = new JButton();
        exitButton = new JButton();

        CreateNButton = new JButton();
        JoinNButton = new JButton();
        CancelCNButton = new JButton();
        CancelJNButton = new JButton();
        CancelSButton = new JButton();
        CancelRButton = new JButton();
        SendButton = new JButton();
        RefreshIPButton = new JButton();

        ImageLabel = new JLabel();
        ProgressBar = new JProgressBar();
        //Variable initialization Ends...

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(600, 300));
        getContentPane().setLayout(null);

        for (int i = 0; i < 5; i++) {
            dot[i] = new DotPanels();
            dot[i].setVisible(false);
            add(dot[i]);
            dot[i].setBounds(0, 0, 600, 300);
        }

        MainPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        MainPanel.setOpaque(false);
        MainPanel.setLayout(null);
        MainPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                initialClick = evt.getPoint();
            }
        });
        MainPanel.addMouseMotionListener(new MouseMotionAdapter() {
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

        HeaderLabel.setFont(new Font("Tahoma", 0, 24));
        HeaderLabel.setForeground(new Color(255, 255, 255));
        HeaderLabel.setText("Click to Connect!");
        MainPanel.add(HeaderLabel);
        HeaderLabel.setBounds(10, 10, 470, 29);

        ipDisplayLabel.setFont(new Font("Tahoma", 0, 20));
        ipDisplayLabel.setForeground(new Color(255, 255, 255));
        ipDisplayLabel.setText("Click on Get IP Button to know your IP");
        MainPanel.add(ipDisplayLabel);
        ipDisplayLabel.setBounds(10, 220, 420, 20);

        StatusLabel.setFont(new Font("Tahoma", 0, 18));
        StatusLabel.setForeground(new Color(255, 255, 255));
        MainPanel.add(StatusLabel);
        StatusLabel.setBounds(10, 270, 440, 22);

        RefreshIPButton.setFont(new Font("Tahoma", 0, 15));
        RefreshIPButton.setText("Get IP");
        RefreshIPButton.setForeground(new Color(255, 255, 255));
        RefreshIPButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        RefreshIPButton.setContentAreaFilled(false);
        RefreshIPButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //NEWUTILITY
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    String str[] = inet.toString().split("/");
                    System.out.println("Your IP: " + str[1]);

                    if (str[1].equals("127.0.0.1")) {
                        ipDisplayLabel.setText("Connrct your PC to a Wireless network or LAN");
                    } else {
                        ipDisplayLabel.setText("Your IP Address is: " + str[1]);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        RefreshIPButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                RefreshIPButton.setForeground(new Color(0, 0, 0));
                StatusLabel.setText("Change Settings...");
                guiPanel.y = 70;
                guiPanel.flag = true;
                guiPanel.repaint();
                RefreshIPButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                RefreshIPButton.setForeground(new Color(255, 255, 255));
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                RefreshIPButton.setContentAreaFilled(false);
            }

        });
        RefreshIPButton.setVisible(true);
        MainPanel.add(RefreshIPButton);
        RefreshIPButton.setBounds(445, 70, 140, 40);

        SettingsButton.setFont(new Font("Tahoma", 0, 15));
        SettingsButton.setText("Settings");
        SettingsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        SettingsButton.setContentAreaFilled(false);
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set.sett();
            }
        });
        SettingsButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                StatusLabel.setText("Change Settings...");
                guiPanel.y = 115;
                guiPanel.flag = true;
                guiPanel.repaint();
                SettingsButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                SettingsButton.setContentAreaFilled(false);
            }

        });
        SettingsButton.setVisible(true);
        MainPanel.add(SettingsButton);
        SettingsButton.setBounds(445, 115, 140, 40);

        exitButton.setFont(new Font("Tahoma", 0, 15));
        exitButton.setText("Exit");
        exitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        //exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                StatusLabel.setText("Leave the Application...");
                guiPanel.y = 250;
                guiPanel.flag = true;
                guiPanel.repaint();
                //exitButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                //exitButton.setContentAreaFilled(false);
            }

        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (Server.status == true || Client.status == true) {
                    try {
                        Server.status = false;
                        Client.status = false;
                        byte msg[] = "Exit".getBytes();
                        Streams.os.write(msg);
                        Streams.s.close();
                        System.out.println("All streams closed, GoodBye!");

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                new RestoreIPs();
                System.exit(0);
            }
        });
        MainPanel.add(exitButton);
        exitButton.setBounds(445, 250, 140, 40);

        CreateNButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        CreateNButton.setText("Create Network");
        CreateNButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CreateNButton.setContentAreaFilled(false);
        CreateNButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StatusLabel.setText("Click to Create Network...");
                guiPanel.y = 160;
                guiPanel.flag = true;
                guiPanel.repaint();
                CreateNButton.setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                CreateNButton.setContentAreaFilled(false);
            }
        });
        CreateNButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNButtonActionPerformed(evt);
            }
        });
        MainPanel.add(CreateNButton);
        CreateNButton.setBounds(445, 160, 140, 40);

        CancelCNButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        CancelCNButton.setText("Destroy Network");
        CancelCNButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CancelCNButton.setContentAreaFilled(false);
        CancelCNButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                StatusLabel.setText("Click to destroy Network...");
                guiPanel.y = 160;
                guiPanel.flag = true;
                guiPanel.repaint();
                CancelCNButton.setContentAreaFilled(true);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                CancelCNButton.setContentAreaFilled(false);
            }
        });
        CancelCNButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCNButtonActionPerformed(evt);
            }
        });
        MainPanel.add(CancelCNButton);
        CancelCNButton.setBounds(445, 160, 140, 40);
        CancelCNButton.setVisible(false);

        JoinNButton.setFont(new Font("Tahoma", 0, 15));
        JoinNButton.setText("Join Network");
        JoinNButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        JoinNButton.setContentAreaFilled(false);
        JoinNButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                StatusLabel.setText("Click to Join Network...");
                guiPanel.y = 205;
                guiPanel.flag = true;
                guiPanel.repaint();
                JoinNButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                JoinNButton.setContentAreaFilled(false);
            }
        });
        JoinNButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                joinNButtonActionPerformed(evt);
            }
        });
        MainPanel.add(JoinNButton);
        JoinNButton.setBounds(445, 205, 140, 40);

        CancelJNButton.setFont(new Font("Tahoma", 0, 15));
        CancelJNButton.setText("Destory Network");
        CancelJNButton.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        CancelJNButton.setContentAreaFilled(false);
        CancelJNButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                StatusLabel.setText("Click to destroy Network...");
                guiPanel.y = 205;
                guiPanel.flag = true;
                guiPanel.repaint();
                CancelJNButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                StatusLabel.setText("");
                guiPanel.flag = false;
                guiPanel.repaint();
                CancelJNButton.setContentAreaFilled(false);
            }
        });
        CancelJNButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelJNButtonActionPerformed(evt);
            }
        });
        MainPanel.add(CancelJNButton);
        CancelJNButton.setBounds(445, 205, 140, 40);
        CancelJNButton.setVisible(false);

        SendButton.setFont(new Font("Tahoma", 0, 15)); // NOI18N
        SendButton.setForeground(new Color(0, 0, 0));
        SendButton.setText("Send");
        SendButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        //SendButton.setContentAreaFilled(false);
        SendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });
        SendButton.setVisible(false);
        MainPanel.add(SendButton);
        SendButton.setBounds(445, 115, 140, 40);

        CancelSButton.setFont(new Font("Tahoma", 0, 15)); // NOI18N
        CancelSButton.setForeground(new Color(0, 0, 0));
        CancelSButton.setText("Cancel Send");
        CancelSButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        //SendButton.setContentAreaFilled(false);
        CancelSButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelSButtonActionPerformed(evt);
            }
        });
        CancelSButton.setVisible(false);
        MainPanel.add(CancelSButton);
        CancelSButton.setBounds(445, 115, 140, 40);

        CancelRButton.setFont(new Font("Tahoma", 0, 15)); // NOI18N
        CancelRButton.setForeground(new Color(0, 0, 0));
        CancelRButton.setText("Cancel Send");
        CancelRButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        //SendButton.setContentAreaFilled(false);
        CancelRButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelRButtonActionPerformed(evt);
            }
        });
        CancelRButton.setVisible(false);
        MainPanel.add(CancelRButton);
        CancelRButton.setBounds(445, 115, 140, 40);

        PercentLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        PercentLabel.setForeground(new java.awt.Color(255, 255, 255));
        PercentLabel.setText(" ");
        MainPanel.add(PercentLabel);
        PercentLabel.setBounds(20, 100, 200, 22);

        FileNameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        FileNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        FileNameLabel.setText(" ");
        MainPanel.add(FileNameLabel);
        FileNameLabel.setBounds(20, 77, 300, 22);

        getContentPane().add(MainPanel);
        MainPanel.setBounds(0, 0, 600, 300);

        guiPanel.setOpaque(false);
        guiPanel.setLayout(null);
        getContentPane().add(guiPanel);
        guiPanel.setBounds(0, 0, 600, 300);

        //ProgressBar.setVisible(false);
        ProgressBar.setForeground(new java.awt.Color(102, 255, 0));
        ProgressBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        MainPanel.add(ProgressBar);
        ProgressBar.setBounds(10, 255, 420, 14);

        ImageLabel.setForeground(new java.awt.Color(255, 255, 255));
        ImageLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Arrow.jpg"))); // NOI18N
        ImageLabel.setText("ImageLabel");
        getContentPane().add(ImageLabel);
        ImageLabel.setBounds(0, 0, 600, 300);

    }
    //End of Constructor...

    private void createNButtonActionPerformed(ActionEvent evt) {
        HeaderLabel.setText("Waiting For SomeOne To Connect");
        JoinNButton.setEnabled(false);
        SettingsButton.setEnabled(false);

        CreateNButton.setVisible(false);
        CancelCNButton.setVisible(true);

        startDots();
        startServer();//It is Thread: so further steps are in this func.//lke changing headerlabel string

    }

    private void cancelCNButtonActionPerformed(ActionEvent evt) {
        Thread wait = new Thread() {
            public void run() {
                try {
                    stopDots();
                    CancelCNButton.setEnabled(false);//
                    Thread.sleep(3500);              //
                    CancelCNButton.setEnabled(true); //prevent multiple waits

                    HeaderLabel.setText("Click to Connect!");
                    JoinNButton.setEnabled(true);

                    SettingsButton.setEnabled(true);

                    CreateNButton.setVisible(true);
                    CancelCNButton.setVisible(false);
                } catch (Exception e) {
                    System.out.println("waiting did not work in cancelCNButton func!");
                }

            }
        };
        wait.start();

        stopServer();

        JoinNButton.setVisible(true);
        SendButton.setVisible(false);
    }

    private void joinNButtonActionPerformed(ActionEvent evt) {
        HeaderLabel.setText("Trying To Connect!");
        CreateNButton.setEnabled(false);
        SettingsButton.setEnabled(false);
        JoinNButton.setVisible(false);
        
        CancelJNButton.setVisible(true);
        CancelJNButton.setEnabled(false);
        startClient();

    }

    private void cancelJNButtonActionPerformed(ActionEvent evt) {
        MainFrame.HeaderLabel.setText("Click to connect!");

        MainFrame.SendButton.setVisible(false);
        MainFrame.JoinNButton.setVisible(true);
        MainFrame.CreateNButton.setVisible(true);
        MainFrame.CancelJNButton.setVisible(false);

        MainFrame.SettingsButton.setEnabled(false);
        MainFrame.CreateNButton.setEnabled(false);
        MainFrame.JoinNButton.setEnabled(false);

        ProgressBar.setValue(0);

        Thread wait = new Thread() {
            public void run() {
                try {
                    Thread.sleep(3500);
                    CreateNButton.setEnabled(true);
                    SettingsButton.setEnabled(true);
                    JoinNButton.setEnabled(true);
                    StatusLabel.setText("Click a Button To Connect!");
                } catch (Exception e) {
                    System.out.println("Disabling did not work!");
                }

            }
        };
        wait.start();

        stopClient();
    }

    private void sendButtonActionPerformed(ActionEvent evt) {
        if (SendButton.getText().equals("Send")) {
            Thread sending = new Thread() {
                public void run() {
                    //SendButton.setEnabled(false);
                    SendFile.Fend();
                }
            };
            sending.start();

        } else if (SendButton.getText().equals("Cancel Receiving")) {
            SendButton.setText("Send");
            ReceiveFile.AbortTransfer = true;
            stopRec();
            Thread t = new Thread() {
                public void run() {
                    try {
                        ReceiveFile.receive.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        } else if (SendButton.getText().equals("Cancel Sending")) {
            SendButton.setText("Send");
            SendFile.AbortTransfer = true;
            SendFile.stoploop = true;
            stopSen();
            Thread t = new Thread() {
                public void run() {
                    try {
                        SendFile.ss.close();
                        SendFile.server.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    }

    private void cancelSButtonActionPerformed(ActionEvent evt) {

    }

    private void cancelRButtonActionPerformed(ActionEvent evt) {

    }

    public static void start() throws Exception {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame = new MainFrame();
                frame.setLocationRelativeTo(null);
                frame.setUndecorated(true);
                frame.setVisible(true);
            }
        });

    }

    public static void main(String args[]) throws Exception {
        start();
    }

    //For GUI besides the JButtons...
    public class GUIPanel extends JPanel {

        boolean flag = false;
        int y;

        public void paint(Graphics g) {
            if (flag == true) {
                super.paint(g);
                g.setColor(Color.white);
                g.fillRect(590, y, 10, 40);
            }
        }

    }
    //end...

    //DotPanels classes and functions...
    public static void pause() {
        try {
            Thread.sleep(150);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //end...

    //here starts frequently used functions...
    static boolean StopDots = false;

    public void startDots() {
        StopDots = false;
        Thread DotMove = new Thread() {
            public void run() {
                Thread moveDot[] = new Thread[5];
                for (int i = 0; i < 5; i++) {
                    dot[i].setVisible(true);
                    moveDot[i] = new Threads(dot[i]);
                    moveDot[i].start();
                    pause();
                }
            }
        };
        DotMove.start();
    }

    public void stopDots() {
        StopDots = true;
    }

    public void startServer() {
        Thread ServerThread = new Thread() {
            public void run() {
                Server.firstConnection();
                stopDots();
                if (Server.status == true) {
                    Streams.stream();
                    StatusLabel.setText("Connected to: " + ConnectedIP);

                    JoinNButton.setVisible(false);
                    SendButton.setBounds(445, 205, 140, 40);
                    SendButton.setVisible(true);
                }
            }
        };
        ServerThread.start();
    }

    public void stopServer() {
        if (Server.status == true) {
            try {
                desConn();
                Server.ss.close();
                Server.s.close();
                Server.status = false;
                System.out.println("Server: CLosed at 3000.");
            } catch (Exception e) {
                System.out.println("Server: Closed at 3000 (earlier connected).");
            }
        }
        //if server is started but no connection is made
        if (Server.status == false) {
            try {
                Server.ss.close();
                Server.s.close();

                System.out.println("Server: CLosed at 3000.");
            } catch (Exception e) {
                System.out.println("Server: Closed at 3000 (earlier disconnected).");
            }
        }
    }

    public void startClient() {
        JoinNButton.setEnabled(false);
        
        try {
            ClientFrame frame = new ClientFrame();
            frame.start(frame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopClient() {
        try {
            desConn();
            Client.status = false;
            Client.client.close();
            System.out.println("Client Closed at port 3000!");
        } catch (Exception e) {
            System.out.println("Client Not Closed!");
        }
    }

    public void desConn() {
        byte msg[] = "destroy".getBytes();
        try {
            Streams.os.write(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopSen() {
        byte msg[] = "CR".getBytes();
        try {
            Streams.os.write(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void stopRec() {
        byte msg[] = "CS".getBytes();
        try {
            Streams.os.write(msg);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

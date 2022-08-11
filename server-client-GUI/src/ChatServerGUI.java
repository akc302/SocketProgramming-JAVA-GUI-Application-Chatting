
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ChatServerGUI extends javax.swing.JFrame {


    public ChatServerGUI() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAllChat = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButtonSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSendMessage = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Waiting to be connected");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new java.awt.BorderLayout(15, 15));

        jTextAreaAllChat.setBackground(new java.awt.Color(255, 240, 255));
        jTextAreaAllChat.setColumns(20);
        jTextAreaAllChat.setRows(5);
        jTextAreaAllChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTextAreaAllChat);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout(15, 15));

        jButtonSend.setBackground(new java.awt.Color(255, 255, 140));
        jButtonSend.setText("Send");
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonSend, java.awt.BorderLayout.LINE_END);

        jTextAreaSendMessage.setBackground(new java.awt.Color(255, 250, 255));
        jTextAreaSendMessage.setColumns(20);
        jTextAreaSendMessage.setRows(5);
        jTextAreaSendMessage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(jTextAreaSendMessage);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        setBounds(0, 0, 369, 398);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        // TODO add your handling code here:
        String msg = jTextAreaSendMessage.getText();
        writer.println(msg);
        jTextAreaSendMessage.setText("");
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try {
            serverSocket = new ServerSocket(5678);
            socket = serverSocket.accept();
            setTitle("Server Connected to "+socket.getInetAddress().getHostAddress()+":"+socket.getPort());
            writer = new PrintWriter(socket.getOutputStream(),true);
            scanner = new Scanner(socket.getInputStream());
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        String input = scanner.nextLine();
                        
                        
                        jTextAreaAllChat.append("Client: "+input+"\n");
                        
                    }
                }
            });
            t.start();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_formWindowOpened


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatServerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSend;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaAllChat;
    private javax.swing.JTextArea jTextAreaSendMessage;
    // End of variables declaration//GEN-END:variables
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter writer;
    private Scanner scanner;
}

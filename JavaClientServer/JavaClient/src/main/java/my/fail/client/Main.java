/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javafx.application.Application;
import my.fail.BufferConverter;

/**
 *
 * @author ���0������ �0��
 */
public class Main{

    static byte[] ln = new byte[]{13, 10};

    static BufferedReader console;
    static String buf;
    static ClientManager client;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        
        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(() -> {
            new ClientWindow().setVisible(true);
        });*/
        
        Application.launch(FXClientWindow.class, args);
        
        /*if (Init()) {
            String exit = "(e(xit)?|q(uit)?)$";
            String stop = "s(top)?$";
            Pattern connect = Pattern.compile("(?<cmd>c(onnect)?)( (?<ip>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:(?<port>\\d{1,5}))?)?$");

            do {
                try {
                    //if (console.ready()) {
                        buf = console.readLine();

                        if (buf.matches(exit)) {
                            client.close();
                            console.close();

                            break;
                        } else if (buf.matches(stop)) {
                            client.Stop();
                        } else if (Pattern.matches(connect.pattern(), buf)) {
                            Matcher match = connect.matcher(buf);

                            String ip = match.group("ip");
                            String port = match.group("port");

                            client.Connect(ip, port);
                        } else {
                            client.StartSession(buf);
                        }
                    //}
                } catch (IOException ex) {
                    System.out.println(ex.getLocalizedMessage());
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            } while (true);

            Delete();
        }*/
    }

    static boolean Init() {
        try {
            console = new BufferedReader(new InputStreamReader(System.in, "UTF-16"));
            buf = "";
            client = new ClientManager();

            return true;
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    static void Delete() {
        try {
            client.close();
            console.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author ���0������ �0��
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

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

    /*static BufferedReader console;
    static String buf;
    static ClientManager client;
    
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
    }*/
}

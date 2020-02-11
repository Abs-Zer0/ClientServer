/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author
 */
public class Main {

    static String buf;
    static BufferedReader console;
    static ServerManager server;

    static void Init() {
        buf = new String();
        console = new BufferedReader(new InputStreamReader(System.in));
        server = new ServerManager();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Init();

        while (true) {
            try {
                buf = console.readLine();
                if (buf.toLowerCase().matches("(e(xit)?|q(uit)?)$")) {
                    server.Stop();
                    console.close();
                    break;
                } else if (buf.toLowerCase().matches("s(tart)?$")) {
                    server.Start(null);
                } else if (buf.toLowerCase().matches("stop")) {
                    server.Stop();
                } else if (buf.toLowerCase().matches("p(rint)?$")) {
                    if (server.GetClients().isEmpty()) {
                        System.out.println("Empty");
                    } else {
                        server.GetClients().forEach((cl) -> {
                            System.out.println(server.GetClients().indexOf(cl) + ": " + cl.Address());
                        });
                    }
                }
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

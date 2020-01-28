/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

import my.fail.BufferConverter;

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
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String buf = "";
        ClientManager client = new ClientManager();

        String exit = "e(xit)?$|q(uit)?$";
        String stop = "s(top)?$";
        Pattern connect = Pattern.compile("(?<cmd>c(onnect)?)( (?<ip>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:(?<port>\\d{1,5}))?)?$");

        /*do {
            try {
                if (console.ready()) {
                    buf = console.readLine();

                    if (Pattern.matches(exit, buf)) {
                        client.close();
                        console.close();

                        break;
                    } else if (Pattern.matches(stop, buf)) {
                        client.Stop();
                    } else if (Pattern.matches(connect.pattern(), buf)) {
                        Matcher match = connect.matcher(buf);

                        String ip = match.group("ip");
                        String port = match.group("port");

                        client.Connect(ip, port);
                    } else {
                        client.StartSession(buf);
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);*/
        try {
            client.close();
            console.close();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        int a = 16777216+65536+256+1;
        System.out.println(a);
        byte[] arr = BufferConverter.IntToBuff(a);
        for (int i = 0; i < arr.length; i++) {
            System.out.print((int) arr[i] + " ");
        }
        System.out.println();
        System.out.println(BufferConverter.BuffToInt(arr));
    }
}

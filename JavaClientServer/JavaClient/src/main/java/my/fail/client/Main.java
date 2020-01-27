/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.util.regex.*;
import java.util.stream.Stream;
import jdk.jfr.Unsigned;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String regex = "(?<cmd>c(onnect)?)( (?<ip>\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(:(?<port>\\d{1,5}))?)?$";
        Pattern p = Pattern.compile(regex);
        String text = "connect 127.0.0.1";
        Matcher m = p.matcher(text);

        System.out.println(m.matches());
        System.out.println(m.group("cmd"));
        System.out.println(m.group("ip"));
        System.out.println(m.group("port"));

        byte[] arr = {0, 0, 0, 1};
        Integer a = 127;
    }

}

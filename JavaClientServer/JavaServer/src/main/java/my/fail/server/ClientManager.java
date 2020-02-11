/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.fail.BufferConverter;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class ClientManager extends Thread {

    private Socket socket;
    private boolean working;
    private final static int timeout = 2000;

    public ClientManager(Socket s) {
        try {
            this.socket = s;
            if (this.socket.getSoTimeout() == 0) {
                this.socket.setSoTimeout(timeout);
            }

            this.working = true;
        } catch (SocketException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            this.working = false;
        }
    }

    @Override
    public void run() {
        while (this.working) {
            try {
                String text = this.ReceiveAll();
                if (!text.equalsIgnoreCase("exit") && !text.equalsIgnoreCase("quit")) {
                    System.out.println("(" + this.socket.getInetAddress().toString() + ") --> " + text);

                    this.SendAll(text);
                    System.out.println("(" + this.socket.getInetAddress().toString() + ") <-- " + text);
                } else {
                    this.working = false;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            this.socket.getInputStream().close();
            this.socket.getOutputStream().close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Stop() {
        this.working = false;
    }

    public String Address() {
        return this.socket.getInetAddress().toString();
    }

    public boolean IsWorking() {
        return this.working;
    }

    private void SendAll(String txt) throws IOException {
        int len = txt.length();
        byte[] arrLen = BufferConverter.IntToBuff(len);

        this.socket.getOutputStream().write(arrLen);
        this.socket.getOutputStream().write(txt.getBytes());
    }

    private String ReceiveAll() throws IOException {
        byte[] arrLen = new byte[Integer.BYTES];
        int curLen = 0;

        while (curLen < Integer.BYTES) {
            int code = this.socket.getInputStream().read(arrLen, curLen, Integer.BYTES - curLen);

            if (code < 0) {
                return "Error in receiving";
            }

            curLen += code;
        }

        int len = BufferConverter.BuffToInt(arrLen);
        byte[] data = new byte[len];
        curLen = 0;

        while (curLen < len) {
            int code = this.socket.getInputStream().read(data, curLen, len - curLen);

            if (code < 0) {
                return "Error in receiving";
            }

            curLen += code;
        }

        return new String(data);
    }
}

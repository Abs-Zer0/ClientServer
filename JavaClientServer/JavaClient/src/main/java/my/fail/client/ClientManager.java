/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import my.fail.BufferConverter;

/**
 *
 * @author abs-zer0
 */
public class ClientManager implements Closeable {

    private Socket socket;
    private final static int timeout = 2000;

    public ClientManager() {
        this.socket = new Socket();
    }

    public void Stop() throws IOException {
        this.socket.close();
    }

    public void Connect(String ip, String port) throws IOException {
        if (this.socket.isClosed()) {
            this.socket = new Socket();
        }

        String correct_ip = ip == null ? "127.0.0.1" : ip;
        short correct_port = port == null ? 0 : Short.valueOf(port);

        this.socket.connect(new InetSocketAddress(correct_ip, correct_port), timeout);
        this.socket.setSoTimeout(timeout);
    }

    public String StartSession(String text) {
        if (!this.IsClosed()) {
            return "Stopped";
        }

        if (!this.IsConnected()) {
            return "Disconnected";
        }

        try {
            this.SendAll(text);
            String result = this.ReceiveAll();

            return result;
        } catch (IOException ex) {
            Logger.getLogger(ClientManager.class.getName()).log(Level.SEVERE, null, ex);

            return ex.getMessage();
        }
    }

    @Override
    public void close() throws IOException {
        this.Stop();
    }

    public boolean IsConnected() {
        return this.socket.isConnected();
    }

    public boolean IsClosed() {
        return this.socket.isClosed();
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

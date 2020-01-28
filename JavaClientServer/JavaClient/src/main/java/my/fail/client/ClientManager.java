/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.Closeable;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author abs-zer0
 */
public class ClientManager implements Closeable {

    private Socket socket;

    public ClientManager() {

    }

    public void Stop() {

    }

    public void Connect(String ip, String port) {

    }

    public void StartSession(String text) {

    }

    @Override
    public void close() throws IOException {
        this.Stop();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.server;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class ServerManager implements Closeable {

    private ServerSocket listener;
    private ArrayList<ClientManager> clients;
    private Thread thr;
    private boolean working;
    private final static int timeout = 2000;

    private final Object locker = new Object();

    public ServerManager() {
        TryCreateListener();
        this.clients = new ArrayList<>();
        working = false;
    }

    public void Start(String port) throws IOException {
        if (this.listener.isClosed()) {
            TryCreateListener();
        }

        short correct_port = port == null ? 0 : Short.valueOf(port);
        this.listener.bind(new InetSocketAddress(correct_port));
        this.listener.setSoTimeout(timeout);

        this.thr = new Thread(() -> MainLoop());
        this.thr.start();
    }

    public void Stop() throws IOException {
        this.working = false;
        try {
            if (thr != null) {
                this.thr.join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listener.close();

        synchronized (locker) {
            clients.stream().map((client) -> {
                client.Stop();
                return client;
            }).forEachOrdered((client) -> {
                try {
                    client.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            clients.clear();
        }
    }

    @Override
    public void close() throws IOException {
        this.Stop();
    }

    public final ArrayList<ClientManager> GetClients() {
        return this.clients;
    }

    private void TryCreateListener() {
        try {
            this.listener = new ServerSocket();
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void MainLoop() {
        this.working = true;
        while (this.working) {
            try {
                Socket socket = this.listener.accept();
                ClientManager client = new ClientManager(socket);
                client.start();

                synchronized (locker) {
                    clients.add(client);
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.RemoveUnused();
        }
    }

    private void RemoveUnused() {
        synchronized (locker) {
            for (int i = 0; i < clients.size();) {
                if (!clients.get(i).IsWorking()) {
                    clients.remove(i);
                } else {
                    i++;
                }
            }
        }
    }

    public void RemoveClient(String addr) {
        synchronized (locker) {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).Address().equalsIgnoreCase(addr)) {
                    clients.get(i).Stop();
                    try {
                        clients.get(i).join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clients.remove(i);
                }
            }
        }
    }
}

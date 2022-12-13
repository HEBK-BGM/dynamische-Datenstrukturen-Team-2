package de.hebk.multiplayer;

import de.hebk.model.list.List;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private SSLServerSocket sslServerSocket;
    private Thread thread;
    private List<ClientConnection> connections = new List<>();

    public void start(int port) {
        System.out.println("Starting server...");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
                    sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(port);

                    SSLSocket socket;
                    while (true) {
                        socket = (SSLSocket) sslServerSocket.accept();
                        connections.insert(new ClientConnection(socket));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    public void stop() {
        System.out.println("Stopping server...");

        thread.stop();
        try {
            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                ClientConnection c = connections.getObject();
                c.closeConnection();
                connections.remove();
            }

            sslServerSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientConnection> getConnections() {
        return connections;
    }
}
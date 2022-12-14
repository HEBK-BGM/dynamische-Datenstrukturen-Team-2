package de.hebk.multiplayer;

import de.hebk.model.list.List;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Server {
    private ServerSocket serverSocket;
    private Thread thread;
    private List<ClientConnection> connections = new List<>();

    public void start(int port, String gamemode) {
        System.out.println("Starting server...");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);

                    Socket socket;
                    while (true) {
                        socket = serverSocket.accept();
                        System.out.println("new connection");
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

            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ClientConnection> getConnections() {
        return connections;
    }
}
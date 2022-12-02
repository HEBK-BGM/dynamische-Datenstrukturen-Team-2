package de.hebk.multiplayer;

import de.hebk.model.list.List;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private List<ClientConnection> connections = new List<>();

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);

            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                connections.insert(new ClientConnection(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package de.hebk.multiplayer;

import de.hebk.gui.MultiplayerLobbyGui;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;

import javax.net.ssl.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Server extends Thread {
    private StartGui gui;
    private JLabel playerLabel;
    private String gamemode;
    private int port;
    private ServerSocket serverSocket;
    private List<ClientConnection> connections = new List<>();

    public Server(StartGui frame, int port, String gamemode) {
        this.gui = frame;
        this.port = port;
        this.gamemode = gamemode;
    }

    public void setPlayerLabel(JLabel playerLabel) {
        this.playerLabel = playerLabel;
    }

    public void run() {
        System.out.println("[Server] Starting server...");

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Socket socket;
        while (true) {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("[Server] new connection");
            ClientConnection conn = new ClientConnection(socket);

            if (!connections.isEmpty()) {
                playerLabel.setText(playerLabel.getText() + ",");
            }

            String username = conn.connect();
            playerLabel.setText(playerLabel.getText() + " " + username);
            gui.setVisible(true);

            Packet playerJoinPacket = new Packet(PacketType.PLAYER_JOIN, username);

            connections.toFirst();
            ClientConnection tmpConn = null;
            while (connections.getObject() != null) {
                tmpConn = connections.getObject();

                tmpConn.send(playerJoinPacket);

                connections.next();
                tmpConn = connections.getObject();
            }

            connections.insert(conn);
        }
    }

    public void stopServer() {
        System.out.println("Stopping server...");

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
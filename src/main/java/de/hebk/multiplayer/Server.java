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
    private String gamemode;
    private int port;
    private ServerSocket serverSocket;
    private List<ClientConnection> connections = new List<>();

    public Server(int port, String gamemode) {
        this.port = port;
        this.gamemode = gamemode;
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
            String username = " " + conn.connect();

            String players = "";
            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                if (i != 0) {
                    players += ",";
                }
                players += connections.getObject().getUsername();
                connections.next();
            }
            players = " " + players;
            Packet allPlayers = new Packet(PacketType.ALL_PLAYERS, players);
            conn.send(allPlayers);

            if (!players.equals(" ")) {
                username = "," + username;
            }

            Packet playerJoinPacket = new Packet(PacketType.PLAYER_JOIN, username);
            connections.insert(conn);
            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                System.out.println("Sending username " + username + " to " + connections.getObject().getUsername());
                connections.getObject().send(playerJoinPacket);
                connections.next();
            }
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
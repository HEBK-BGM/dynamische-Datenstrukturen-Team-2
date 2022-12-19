package de.hebk.multiplayer;

import com.google.gson.Gson;
import de.hebk.Config;
import de.hebk.Question;
import de.hebk.SQLManager;
import de.hebk.gamemodes.mutliplayer.MultiplayerHardcore;
import de.hebk.gamemodes.mutliplayer.MultiplayerNormal;
import de.hebk.gamemodes.mutliplayer.MultiplayerTrueOrNot;
import de.hebk.model.list.List;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private String gamemode;
    private Gson gson;
    private int port;
    private ServerSocket serverSocket;
    private List<ClientConnection> connections = new List<>();
    private List<Question> questionList = new List<>();
    private SQLManager sqlManager;

    /**
     * Contructor for the server
     * @param port      Port where the server should listen for incoming connections
     * @param gamemode  Gamemode
     */
    public Server(int port, String gamemode) {
        this.port = port;
        this.gamemode = gamemode;
    }

    /**
     * Creates a new thread and listens for new connections
     */
    public void run() {
        System.out.println("[Server] Starting server...");
        gson = new Gson();
        sqlManager = new SQLManager(Config.getDatabaseURL());
        
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
                System.out.println("Sending username " + username.substring(2) + " to " + connections.getObject().getUsername());
                connections.getObject().send(playerJoinPacket);
                connections.next();
            }
        }
    }

    /**
     * Starts the game
     */
    public void startGame() {
        Packet p = new Packet(PacketType.CLEAR, "");
        connections.toFirst();
        for (int i = 0; i < connections.size(); i++) {
            connections.getObject().send(p);
            connections.next();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (gamemode.equals("Normal")) {
                    MultiplayerNormal multiplayerNormal = new MultiplayerNormal(connections, sqlManager);
                    multiplayerNormal.startGame();
                }
                else if (gamemode.equals("Hardcore")) {
                    MultiplayerHardcore multiplayerHardcore = new MultiplayerHardcore(connections, sqlManager);
                    multiplayerHardcore.startGame();
                }
                else if (gamemode.equals("True or Not")) {
                    MultiplayerTrueOrNot multiplayerTrueOrNot = new MultiplayerTrueOrNot(connections, sqlManager);
                    multiplayerTrueOrNot.startGame();
                }
            }
        });
        thread.start();
    }

    /**
     * Stops the server and closes every connection
     */
    public void stopServer() {
        System.out.println("Stopping server...");

        try {
            Packet closePacket = new Packet(PacketType.CONNECTION_CLOSE, "");
            connections.toFirst();
            for (int i = 0; i < connections.size(); i++) {
                ClientConnection c = connections.getObject();
                c.send(closePacket);
                c.closeConnection();
                connections.remove();
            }

            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns every connection
     * @return Every connection
     */
    public List<ClientConnection> getConnections() {
        return connections;
    }
}
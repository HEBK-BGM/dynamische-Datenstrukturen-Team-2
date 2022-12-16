package de.hebk.multiplayer;

import com.google.gson.Gson;
import de.hebk.gui.MultiplayerLobbyGui;
import de.hebk.gui.StartGui;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Gson gson;
    private StartGui frame;
    private MultiplayerLobbyGui lobbyGui;
    private String ip;
    private int port;
    private String username;

    public Client(StartGui gui, MultiplayerLobbyGui lobbyGui, String ip, int port, String username) {
        gson = new Gson();
        this.frame = gui;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.lobbyGui = lobbyGui;
    }

    public void run() {
        System.out.println("[Client] Connecting to Server");
        try {
            Thread.sleep(500);

            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Packet packet = new Packet(PacketType.JOIN, username);
            send(packet);
            System.out.println("[Client] Connected to server");

            Packet packet2 = read();
            if (packet2.getPacketType().equals(PacketType.ALL_PLAYERS)) {
                lobbyGui.setMitspielerLabel(lobbyGui.getMitspielerLabel().getText() + packet2.getContent());
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        Packet p = read();
                        if (p.getPacketType().equals(PacketType.PLAYER_JOIN)) {
                            lobbyGui.setMitspielerLabel(lobbyGui.getMitspielerLabel().getText() + p.getContent());
                            System.out.println("Join " + p.getContent());
                        }
                    }
                }
            });
            thread.start();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(Packet packet) {
        try {
            writer.write(gson.toJson(packet));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Packet read() {
        try {
            return gson.fromJson(reader.readLine(), Packet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
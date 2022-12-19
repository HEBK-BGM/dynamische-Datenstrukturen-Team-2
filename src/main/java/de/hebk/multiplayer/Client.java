package de.hebk.multiplayer;

import com.google.gson.Gson;
import de.hebk.Question;
import de.hebk.gui.multiplayer.*;
import de.hebk.gui.StartGui;

import javax.swing.*;
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

    /**
     * Contructor for the client
     * @param gui       StartGui to change the rame
     * @param lobbyGui  LobbyGui to change the player label when a player joins
     * @param ip        Server IP
     * @param port      Server Port
     * @param username  Username
     */
    public Client(StartGui gui, MultiplayerLobbyGui lobbyGui, String ip, int port, String username) {
        gson = new Gson();
        this.frame = gui;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.lobbyGui = lobbyGui;
    }

    /**
     * Creates a new thread and connects to the server
     */
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

            while (true) {
                Packet p = read();

                switch (p.getPacketType()) {
                    case PLAYER_JOIN:
                        lobbyGui.setMitspielerLabel(lobbyGui.getMitspielerLabel().getText() + p.getContent());
                        System.out.println("Join " + p.getContent());
                        break;
                    case CLEAR:
                        JPanel panel = new JPanel();
                        frame.setContentPane(panel);
                        frame.revalidate();
                        frame.repaint();
                        break;
                    case CONNECTION_CLOSE:
                        writer.close();
                        reader.close();
                        socket.close();
                        // TODO: Display a new window
                        break;
                    case SELECT_QUESTION:
                        System.out.println("Select question");
                        new MultiplayerSelectQuestionGui(frame, Client.this, gson.fromJson(p.getContent(), String[].class));
                        break;
                    case QUESTION_IS_SELECTED:
                        System.out.println("Question is being selected");
                        new MultiplayerQuestionIsSelectedGui(frame, p.getContent());
                        break;
                    case ASK_QUESTION:
                        new MultiplayerQuestionGui(frame, Client.this, gson.fromJson(p.getContent(), Question.class));
                        break;
                    case WRONG_ANSWER:
                        new MultiplayerInfoGui(frame, "Deine Antwort war leider Falsch!");
                        break;
                    case RIGHT_ANSWER:
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the connection to the server
     */
    public void closeConnection() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a packet to the server
     * @param packet The packet that's going to be send
     */
    public void send(Packet packet) {
        try {
            writer.write(gson.toJson(packet));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads incoming packages
     * @return The packet that was received
     */
    public Packet read() {
        try {
            return gson.fromJson(reader.readLine(), Packet.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
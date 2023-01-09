package de.hebk.multiplayer;

import com.google.gson.Gson;
import de.hebk.game.*;
import de.hebk.gui.multiplayer.*;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

public class Client extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Gson gson;
    private StartGui frame;
    private MultiplayerLobbyGui lobbyGui;
    private MultiplayerJoinGui joinGui;
    private String ip;
    private int port;
    private String username;
    private Joker[] joker = new Joker[3];

    /**
     * Contructor for the client
     * @param gui       StartGui to change the rame
     * @param lobbyGui  LobbyGui to change the player label when a player joins
     * @param joinGui   JoinGui to display an error message
     * @param ip        Server IP
     * @param port      Server Port
     * @param username  Username
     */
    public Client(StartGui gui, MultiplayerLobbyGui lobbyGui, MultiplayerJoinGui joinGui, String ip, int port, String username) {
        gson = new Gson();
        this.frame = gui;
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.lobbyGui = lobbyGui;
        this.joinGui = joinGui;

        joker[0] = new Joker(JokerType.TELEPHONE_JOKER);
        joker[1] = new Joker(JokerType.AUDIENCE_JOKER);
        joker[2] = new Joker(JokerType.HALF_JOKER);
    }

    /**
     * Creates a new thread and connects to the server
     */
    public void run() {
        System.out.println("[Client] Connecting to Server");
        try {
            Thread.sleep(1000);

            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            Packet packet = new Packet(PacketType.JOIN, username);
            lobbyGui.show();
            send(packet);
            System.out.println("[Client] Connected to server");

            Packet allPlayers = read();
            if (allPlayers.getPacketType().equals(PacketType.ALL_PLAYERS)) {
                lobbyGui.setMitspielerLabel(lobbyGui.getMitspielerLabel().getText() + allPlayers.getContent());
            }

            String gamemode = "";
            Packet gamemodePacket = read();
            if (gamemodePacket.getPacketType().equals(PacketType.GAMEMODE)) {
                gamemode = gamemodePacket.getContent();
            }

            System.out.println(gamemode);

            while (true) {
                Packet p = read();

                switch (p.getPacketType()) {
                    case PLAYER_JOIN:
                        playerJoin(p);
                        break;
                    case CLEAR:
                        clearPanel();
                        break;
                    case CONNECTION_CLOSE:
                        closeConnection();
                        return;
                    case SELECT_QUESTION:
                        selectQuestion(p);
                        break;
                    case QUESTION_IS_SELECTED:
                        questionIsSelected(p);
                        break;
                    case ASK_QUESTION:
                        askQuestion(gamemode, p);
                        break;
                    case WRONG_ANSWER:
                        new MultiplayerInfoGui(frame, "Deine Antwort war leider Falsch!");
                        break;
                    case RIGHT_ANSWER:
                        break;
                    case LAST_ALIVE:
                        new MultiplayerLastAliveGui(frame, this);
                        break;
                    case END:
                        endGame(gamemode, p);
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            if (joinGui != null) {
                joinGui.setErrorMessage("Es konnte keine Verbindung hergestellt werden");
            }

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
            new MultiplayerInfoGui(frame, "Verbindung zum Server unterbrochen. Du wirst in 10 Sekunden zum Hauptmenü gebracht.");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        frame.setContentPane(frame.getPanel());
                        frame.revalidate();
                        frame.repaint();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            thread.start();

            throw new RuntimeException(e);
        }
    }

    private void playerJoin(Packet p) {
        lobbyGui.setMitspielerLabel(lobbyGui.getMitspielerLabel().getText() + p.getContent());
        System.out.println("Join " + p.getContent());
    }

    private void clearPanel() {
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void selectQuestion(Packet p) {
        System.out.println("Select question");
        new MultiplayerSelectQuestionGui(frame, Client.this, gson.fromJson(p.getContent(), String[].class));
    }

    private void questionIsSelected(Packet p) {
        System.out.println("Question is being selected");
        new MultiplayerQuestionIsSelectedGui(frame, p.getContent());
    }

    private void askQuestion(String gamemode, Packet p) {
        if (gamemode.equals("Normal")) {
            new MultiplayerNormalGui(frame, Client.this, gson.fromJson(p.getContent(), Question.class), joker);
        }
        else if (gamemode.equals("Hardcore")) {
            new MultiplayerHardcoreGui(frame, Client.this, gson.fromJson(p.getContent(), Question.class), joker);
        }
        else if (gamemode.equals("True or Not")) {
            new MultiplayerTrueOrNotGui(frame, Client.this, gson.fromJson(p.getContent(), Question.class));
        }
    }

    private void endGame(String gamemode, Packet p) {
        SQLManager sqlManager = new SQLManager(Config.getDatabaseURL());
        Calendar cal = Calendar.getInstance();
        int level = Integer.parseInt(p.getContent());
        String date = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.getTime().getMonth()+1) + "." + cal.get(Calendar.YEAR) + " - " + cal.getTime().getHours() + ":" + cal.getTime().getMinutes();

        int money;
        switch (gamemode) {
            case "Normal" -> money = Config.normalLevelToMoney(level);
            case "Hardcore" -> money =  Config.hardcoreLevelToMoney(level);
            case "True or Not" -> money = Config.trueOrNotLevelToMoney(level);
            default -> money = 0;
        }

        Highscore highscore = new Highscore(username, gamemode, Integer.parseInt(p.getContent()), money, date);
        sqlManager.addHighscore(highscore);

        new MultiplayerEndGui(frame, "Das Spiel ist vorbei, ihr habt es bis zum Level " + p.getContent() + " geschafft!");
    }
}
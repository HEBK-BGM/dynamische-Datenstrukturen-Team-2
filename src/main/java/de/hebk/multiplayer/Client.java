package de.hebk.multiplayer;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Gson gson;
    private String ip;
    private int port;
    private String username;

    public Client(String ip, int port, String username) {
        gson = new Gson();
        this.ip = ip;
        this.port = port;
        this.username = username;
    }

    public void run() {
        System.out.println("[Client] Connecting to Server");
        try {
            Thread.sleep(500);

            socket = new Socket(ip, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Packet packet = new Packet(PacketType.JOIN, username);
            send(gson.toJson(packet));

            System.out.println("[Client] Connected to server");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
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

    public void send(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String read() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
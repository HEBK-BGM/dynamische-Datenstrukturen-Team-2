package de.hebk.multiplayer;

import com.google.gson.Gson;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Gson gson;

    public Client(String ip, int port, String username) {
        gson = new Gson();
        connect(ip, port, username);
    }

    public void connect(String ip, int port, String username) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Connecting to Server");
                try {
                    socket = new Socket(ip, port);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    Packet packet = new Packet(PacketType.JOIN, username);
                    send(gson.toJson(packet));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
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
            writer.flush();
            writer.newLine();
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
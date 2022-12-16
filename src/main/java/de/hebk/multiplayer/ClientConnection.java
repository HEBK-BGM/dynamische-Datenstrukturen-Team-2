package de.hebk.multiplayer;

import com.google.gson.Gson;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private String username;
    private Gson gson;
    private BufferedReader reader;
    private BufferedWriter writer;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    public String connect() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            gson = new Gson();

            String content = reader.readLine();
            Packet p = gson.fromJson(content, Packet.class);
            if (p.getPacketType().equals(PacketType.JOIN)) {
                username = p.getContent();
                return username;
            }
            else {
                closeConnection();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getUsername() {
        return username;
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

    public void closeConnection() {
        try {
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

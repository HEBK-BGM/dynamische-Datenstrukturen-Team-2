package de.hebk.multiplayer;

import com.google.gson.Gson;

import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientConnection {
    private Socket socket;
    private String username;
    private Gson gson;
    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean failed = false;


    /**
     * Contructor for a connection to the client
     * @param socket Socket with the connection to the client
     */
    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    /**
     * Connects the server with the client
     * @return Username of the client
     */
    public String connect() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
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

    /**
     * Get the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sends a packet to the client
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

    /**
     * Closes the connection to the client
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
     * Returns if the client has failed
     * @return boolean if the client has failed
     */
    public boolean hasFailed() {
        return failed;
    }

    /**
     * Sets if the client has failed
     * @param failed boolean
     */
    public void setFailed(boolean failed) {
        this.failed = failed;
    }
}

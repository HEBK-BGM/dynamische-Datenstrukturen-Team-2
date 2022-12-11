package de.hebk.multiplayer;

import com.google.gson.Gson;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class Client {
    private SSLSocket sslSocket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private Gson gson;

    public Client(String ip, int port, String username) {
        connect(ip, port, username);
    }

    public void connect(String ip, int port, String username) {
        try {
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            sslSocket = (SSLSocket) sslSocketFactory.createSocket(ip, port);
            reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));

            Packet packet = new Packet(PacketType.JOIN, username);
            send(gson.toJson(packet));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            reader.close();
            writer.close();
            sslSocket.close();
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
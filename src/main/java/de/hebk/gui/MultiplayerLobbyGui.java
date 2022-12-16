package de.hebk.gui;

import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.multiplayer.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerLobbyGui {
    private StartGui frame;
    private Server server;
    private JPanel panel1;
    private JButton startenButton;
    private JButton zurueckButton;
    private JLabel mitspielerLabel;

    public MultiplayerLobbyGui(StartGui gui, Server server, Client client) {
        this.frame = gui;
        this.server = server;
        server.setPlayerLabel(mitspielerLabel);
        server.start();
        client.start();
        frame.add(panel1);
        frame.setVisible(true);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                server.stopServer();
                new MultiplayerCreateGui(frame);
            }
        });

        startenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public MultiplayerLobbyGui(StartGui gui, Client client) {
        this.frame = gui;

        panel1.remove(startenButton);
        frame.add(panel1);
        frame.repaint();
        frame.setVisible(true);

        Packet packet = client.read();

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                new MultiplayerCreateGui(frame);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Packet p = client.read();
                    if (p.getPacketType().equals(PacketType.PLAYER_JOIN)) {
                        mitspielerLabel.setText(mitspielerLabel.getText() + ", " + p.getContent());
                    }
                }
            }
        });
        thread.start();
    }

    private void createUIComponents() {
        mitspielerLabel = new JLabel("Mitspieler: ");
    }
}

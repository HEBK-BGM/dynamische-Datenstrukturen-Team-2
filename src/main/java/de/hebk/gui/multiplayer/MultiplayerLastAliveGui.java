package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerLastAliveGui {
    private JPanel panel1;
    private JButton weiterSpielenButton;
    private JButton aufhoerenButton;

    public MultiplayerLastAliveGui(StartGui gui, Client client) {
        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();

        weiterSpielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.KEEP_PLAYING, "");
                client.send(packet);
            }
        });

        aufhoerenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.STOP_PLAYING, "");
                client.send(packet);
            }
        });
    }
}

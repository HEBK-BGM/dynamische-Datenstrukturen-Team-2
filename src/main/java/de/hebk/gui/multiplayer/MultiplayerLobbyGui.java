package de.hebk.gui.multiplayer;

import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerLobbyGui {
    private StartGui frame;
    private Server server;
    private JPanel panel1;
    private JButton startenButton;
    private JButton zurueckButton;
    private JLabel mitspielerLabel;
    private JLabel errorLabel;

    public MultiplayerLobbyGui(StartGui gui, Server server) {
        this.frame = gui;
        this.server = server;
        server.start();
        frame.add(panel1);

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
                if (!(server.getConnections().size() < 2)) {
                    frame.remove(panel1);
                    server.startGame();
                }
                else {
                    errorLabel = new JLabel("Es gibt zu wenige Mitspieler!");
                    errorLabel.setForeground(Color.RED);
                    panel1.updateUI();
                }
            }
        });
    }

    public MultiplayerLobbyGui(StartGui gui) {
        this.frame = gui;

        startenButton.setVisible(false);
        frame.add(panel1);
        frame.repaint();
        frame.setVisible(true);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                new MultiplayerJoinGui(frame);
            }
        });
    }

    private void createUIComponents() {
        mitspielerLabel = new JLabel("Mitspieler: ");
        errorLabel = new JLabel("");
    }

    public void setMitspielerLabel(String label) {
        this.mitspielerLabel.setText(label);
        frame.setVisible(true);
    }

    public JLabel getMitspielerLabel() {
        return this.mitspielerLabel;
    }
}

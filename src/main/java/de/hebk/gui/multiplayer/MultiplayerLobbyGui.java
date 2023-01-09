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

        frame.setContentPane(panel1);
        frame.revalidate();
        frame.repaint();

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.stopServer();
                new MultiplayerCreateGui(frame);
            }
        });

        startenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(server.getConnections().size() < 2)) {
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

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerJoinGui(frame);
            }
        });
    }

    public void show() {
        frame.setContentPane(panel1);
        frame.revalidate();
        frame.repaint();
    }

    private void createUIComponents() {
        mitspielerLabel = new JLabel("Mitspieler: ");
        errorLabel = new JLabel("");
    }

    public void setMitspielerLabel(String label) {
        this.mitspielerLabel.setText(label);
    }

    public JLabel getMitspielerLabel() {
        return this.mitspielerLabel;
    }
}

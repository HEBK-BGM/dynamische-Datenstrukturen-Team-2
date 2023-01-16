package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerLobbyGui {
    private JImagePanel imagePanel;

    private StartGui frame;
    private Server server;
    private JPanel panel1;
    private JButton startenButton;
    private JButton zurueckButton;
    private JLabel mitspielerLabel;
    private JLabel errorLabel;

    /**
     * Creates a multiplayer lobby gui for the host
     * @param gui       The frame
     * @param server    The server
     */
    public MultiplayerLobbyGui(StartGui gui, Server server) {
        this.frame = gui;
        this.server = server;
        server.start();

        imagePanel = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        imagePanel.add(panel1);

        frame.setContentPane(imagePanel);
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

    /**
     * Creates a multiplayer lobby gui for players
     * @param gui   The frame
     */
    public MultiplayerLobbyGui(StartGui gui) {
        this.frame = gui;
        startenButton.setVisible(false);

        imagePanel = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        imagePanel.add(panel1);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerJoinGui(frame);
            }
        });
    }

    /**
     * Shows the panel
     */
    public void show() {
        frame.setContentPane(imagePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void createUIComponents() {
        mitspielerLabel = new JLabel("Mitspieler: ");
        errorLabel = new JLabel("");
    }

    /**
     * Sets the player label
     * @param label
     */
    public void setMitspielerLabel(String label) {
        this.mitspielerLabel.setText(label);
    }

    /**
     * Gets the player label
     * @return  The player label
     */
    public JLabel getMitspielerLabel() {
        return this.mitspielerLabel;
    }
}

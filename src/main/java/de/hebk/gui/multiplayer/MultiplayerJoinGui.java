package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerJoinGui {
    private StartGui frame;
    private JPanel panel1;
    private JButton beitretenButton;
    private JButton zurueckButton;
    private JTextField usernameField;
    private JTextField serveripField;
    private JTextField portField;
    private JLabel errorLabel;

    /**
     * Contructor for a gui
     * @param frame The frame
     */
    public MultiplayerJoinGui(StartGui frame) {
        this.frame = frame;

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        frame.pack();

        frame.setContentPane(p);
        frame.revalidate();
        frame.repaint();
        errorLabel.setText("");

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerGui(frame);
            }
        });

        beitretenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joinGame(usernameField.getText(), serveripField.getText(), portField.getText());
            }
        });
    }

    /**
     * Joins a game
     * @param username  The username
     * @param ip        The server ip
     * @param port      The server port
     */
    private void joinGame(String username, String ip, String port) {
        if (!(username.length() > 0)) {
            System.out.println("Kein gültiger Username!");
            return;
        }

        String portRegex = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
        if (!port.matches(portRegex)) {
            System.out.println("Kein gültiger Port!");
            return;
        }

        MultiplayerLobbyGui lobbyGui = new MultiplayerLobbyGui(frame);
        Client client = new Client(frame, lobbyGui, this, ip, Integer.parseInt(port), username);
        client.start();
    }

    /**
     * To set the error message if the join fails
     * @param errorMessage   The error message
     */
    public void setErrorMessage(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    private void createUIComponents() {
        errorLabel = new JLabel();
    }
}

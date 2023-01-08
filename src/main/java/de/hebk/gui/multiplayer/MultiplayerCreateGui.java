package de.hebk.gui.multiplayer;

import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerCreateGui {
    private StartGui frame;
    private JPanel panel1;
    private JTextField usernameField;
    private JTextField portField;
    private JButton erstellenButton;
    private JButton zurueckButton;
    private JList list1;

    public MultiplayerCreateGui(StartGui gui) {
        this.frame = gui;
        frame.add(panel1);
        frame.setVisible(true);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                new MultiplayerGui(frame);
            }
        });

        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMultiplayer(usernameField.getText(), portField.getText());
            }
        });
    }

    private void createMultiplayer(String username, String port) {
        if (!(username.length() > 0)) {
            System.out.println("Kein gültiger Username!");
            return;
        }

        String portRegex = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
        if (!port.matches(portRegex)) {
            System.out.println("Kein gültiger Port!");
            return;
        }

        if (list1.isSelectionEmpty()) {
            System.out.println("Kein Spielmodus ausgewählt");
            return;
        }

        String gamemode = list1.getSelectedValue().toString();
        Server server = new Server(Integer.parseInt(port), gamemode);

        frame.remove(panel1);
        MultiplayerLobbyGui lobbyGui = new MultiplayerLobbyGui(frame, server);
        Client client = new Client(frame, lobbyGui, null, "127.0.0.1", Integer.parseInt(port), username);
        client.start();
    }
}
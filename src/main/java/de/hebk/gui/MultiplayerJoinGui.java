package de.hebk.gui;

import javax.swing.*;
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

    public MultiplayerJoinGui(StartGui frame) {
        this.frame = frame;
        frame.add(panel1);
        frame.setVisible(true);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
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

    private void joinGame(String username, String ip, String port) {
        if (!(username.length() > 0)) {
            System.out.println("Kein gültiger Username!");
            return;
        }

        String ipregex = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
        if (!ip.matches(ipregex)) {
            System.out.println("Keine gültige IP");
            return;
        }

        String portRegex = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
        if (!port.matches(portRegex)) {
            System.out.println("Kein gültiger Port!");
            return;
        }
    }
}

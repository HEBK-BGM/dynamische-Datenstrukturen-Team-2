package de.hebk.gui.multiplayer;

import de.hebk.Question;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;

import javax.swing.*;

public class MultiplayerTrueOrNotGui {
    private JPanel panel1;
    private JButton wahrButton;
    private JButton falschButton;
    private JLabel questionLabel;

    public MultiplayerTrueOrNotGui(StartGui frame, Client client, Question question) {
        questionLabel.setText(question.getBody());
        frame.setContentPane(panel1);
    }

    private void createUIComponents() {
        questionLabel = new JLabel("");
    }
}

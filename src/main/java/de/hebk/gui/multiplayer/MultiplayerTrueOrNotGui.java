package de.hebk.gui.multiplayer;

import de.hebk.game.Question;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MultiplayerTrueOrNotGui {
    private JPanel panel1;
    private JButton wahrButton;
    private JButton falschButton;
    private JLabel questionLabel;
    private JLabel statementLabel;

    public MultiplayerTrueOrNotGui(StartGui gui, Client client, Question question) {
        String statement = getStatement(question);
        questionLabel.setText("Frage: " + question.getBody());
        statementLabel.setText("Antwort: " + statement);
        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        String info = "Bitte warte während die anderen Spieler deren Antworten abgeben...";

        wahrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendAnswer(question, statement, client, true);
                new MultiplayerInfoGui(gui, info);
            }
        });

        falschButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendAnswer(question, statement, client, false);
                new MultiplayerInfoGui(gui, info);
            }
        });
    }

    private void createUIComponents() {
        questionLabel = new JLabel("");
        statementLabel = new JLabel("");
    }

    private String getStatement(Question question) {
        Random rand = new Random();

        if (rand.nextInt(100) <= 50) {
            return question.getAnswers()[question.getCorrect()-1];
        }

        int random = rand.nextInt(4);
        while (random == question.getCorrect()-1) {
            random = rand.nextInt(4);
        }

        return question.getAnswers()[random];
    }

    private void sendAnswer(Question question, String statement, Client client, boolean bool) {
        String rightAnswer = question.getCorrectAnswer();

        Packet packet = null;
        if (rightAnswer.equals(statement)) {
            if (bool) {
                packet = new Packet(PacketType.ANSWER, "true");
            }
            else {
                packet = new Packet(PacketType.ANSWER, "false");
            }
        }
        else {
            if (bool) {
                packet = new Packet(PacketType.ANSWER, "false");
            }
            else {
                packet = new Packet(PacketType.ANSWER, "true");
            }
        }

        client.send(packet);
    }
}

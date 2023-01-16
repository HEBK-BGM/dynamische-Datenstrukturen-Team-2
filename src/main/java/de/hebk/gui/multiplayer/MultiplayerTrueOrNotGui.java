package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MultiplayerTrueOrNotGui {
    private JPanel panel1;
    private JButton wahrButton;
    private JButton falschButton;
    private JLabel questionLabel;
    private JLabel statementLabel;

    /**
     * Creates a gui for the true or not game mode
     * @param gui       The frame
     * @param client    The client
     * @param question  The question
     */
    public MultiplayerTrueOrNotGui(StartGui gui, Client client, Question question) {
        String statement = getStatement(question);
        questionLabel.setText("Frage: " + question.getBody());
        statementLabel.setText("Antwort: " + statement);

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);

        gui.setContentPane(p);
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

        if (Config.cheatsEnabled()) {
            if (statement.equals(question.getCorrectAnswer())) {
                wahrButton.setBackground(Color.GREEN);
            }
            else {
                falschButton.setBackground(Color.GREEN);
            }
        }
    }

    private void createUIComponents() {
        questionLabel = new JLabel("");
        statementLabel = new JLabel("");
    }

    /**
     * Gets a random statement from a question
     * @param question  The question
     * @return          The statement
     */
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

    /**
     * Sends an answer to the server
     * @param question  The question
     * @param statement The statement
     * @param client    The client
     * @param bool      Boolen if the player believes the question is true or not
     */
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

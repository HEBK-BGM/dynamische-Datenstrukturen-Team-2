package de.hebk.gui.multiplayer;

import de.hebk.game.Question;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.game.Joker;

import javax.swing.*;
import java.awt.*;

public class MultiplayerHardcoreGui {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton a5050JokerButton;
    private JButton publikumsjokerButton;
    private JButton telefonjokerButton;
    private JLabel timerLabel;
    private JLabel questionLabel;
    private Thread thread;

    public MultiplayerHardcoreGui(StartGui gui, Client client, Question question, Joker[] joker) {
        questionLabel.setText(question.getBody());
        timerLabel.setText("Zeit: 60 Sekunden");
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);

        if (joker[0].isUsed()) {
            telefonjokerButton.setBackground(Color.GRAY);
        }
        if (joker[1].isUsed()) {
            publikumsjokerButton.setBackground(Color.GRAY);
        }
        if (joker[2].isUsed()) {
            a5050JokerButton.setBackground(Color.GRAY);
        }

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        startTimer();
    }

    private void createUIComponents() {
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        a5050JokerButton = new JButton();
        publikumsjokerButton = new JButton();
        telefonjokerButton = new JButton();
        timerLabel = new JLabel();
        questionLabel = new JLabel();
    }

    private void startTimer() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 59; i >= 0; i--) {
                    try {
                        Thread.sleep(1000);
                        timerLabel.setText("Zeit: " + i + " Sekunden");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    private void stopTimer() {
        thread.stop();
    }
}

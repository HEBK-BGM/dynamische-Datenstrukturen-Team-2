package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;
import de.hebk.multiplayer.Client;
import de.hebk.game.Joker;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.sound.SoundManager;
import de.hebk.sound.SoundType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerHardcoreGui {
    private Client client;
    private StartGui gui;
    private SoundManager soundManager;

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

    public MultiplayerHardcoreGui(StartGui gui, SoundManager soundManager, Client client, Question question, Joker[] joker) {
        this.gui = gui;
        this.client = client;
        this.soundManager = soundManager;

        questionLabel.setText(question.getBody());
        timerLabel.setText("Zeit: 60 Sekunden");
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!button1.getText().equals(" ")) {
                    sendAnswer("1");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!button2.getText().equals(" ")) {
                    sendAnswer("2");
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!button3.getText().equals(" ")) {
                    sendAnswer("3");
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!button4.getText().equals(" ")) {
                    sendAnswer("4");
                }
            }
        });

        if (Config.cheatsEnabled()) {
            switch (question.getCorrect()) {
                case 1 -> button1.setBackground(Color.GREEN);
                case 2 -> button2.setBackground(Color.GREEN);
                case 3 -> button3.setBackground(Color.GREEN);
                case 4 -> button4.setBackground(Color.GREEN);
            }
        }

        startTimer();
        loadJoker(joker, question);
    }

    private void loadJoker(Joker[] joker, Question question) {
        if (joker[0].isUsed()) {
            telefonjokerButton.setBackground(Color.GRAY);
        }
        if (joker[1].isUsed()) {
            publikumsjokerButton.setBackground(Color.GRAY);
        }
        if (joker[2].isUsed()) {
            a5050JokerButton.setBackground(Color.GRAY);
        }

        telefonjokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!telefonjokerButton.getBackground().equals(Color.GRAY)) {
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.TELEPHONE_JOKER, true);
                    List<String> answer = joker[0].use(question);
                    answer.toFirst();

                    if (button1.getText().equals(answer.getObject())) {
                        button1.setBackground(Color.GREEN);
                    }
                    if (button2.getText().equals(answer.getObject())) {
                        button2.setBackground(Color.GREEN);
                    }
                    if (button3.getText().equals(answer.getObject())) {
                        button3.setBackground(Color.GREEN);
                    }
                    if (button4.getText().equals(answer.getObject())) {
                        button4.setBackground(Color.GREEN);
                    }
                    telefonjokerButton.setBackground(Color.GRAY);
                }
            }
        });

        publikumsjokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!publikumsjokerButton.getBackground().equals(Color.GRAY)) {
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.AUDIENCE_JOKER, true);
                    List<String> answer = joker[0].use(question);
                    answer.toFirst();

                    if (button1.getText().equals(answer.getObject())) {
                        button1.setBackground(Color.GREEN);
                    }
                    if (button2.getText().equals(answer.getObject())) {
                        button2.setBackground(Color.GREEN);
                    }
                    if (button3.getText().equals(answer.getObject())) {
                        button3.setBackground(Color.GREEN);
                    }
                    if (button4.getText().equals(answer.getObject())) {
                        button4.setBackground(Color.GREEN);
                    }
                    publikumsjokerButton.setBackground(Color.GRAY);
                }
            }
        });

        a5050JokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!a5050JokerButton.getBackground().equals(Color.GRAY)) {
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.HALF_JOKER, false);
                    soundManager.playNext(SoundType.QUESTION, true);
                    List<String> answer = joker[2].use(question);
                    answer.toFirst();

                    for (int i = 0; i < answer.size(); i++) {
                        if (button1.getText().equals(answer.getObject())) {
                            button1.setText(" ");
                        }
                        if (button2.getText().equals(answer.getObject())) {
                            button2.setText(" ");
                        }
                        if (button3.getText().equals(answer.getObject())) {
                            button3.setText(" ");
                        }
                        if (button4.getText().equals(answer.getObject())) {
                            button4.setText(" ");
                        }
                        answer.next();
                    }
                    a5050JokerButton.setBackground(Color.GRAY);
                }
            }
        });
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

                        if (i == 0) {
                            sendAnswer("5");
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    private void sendAnswer(String answer) {
        Packet packet = new Packet(PacketType.ANSWER, answer);
        client.send(packet);

        if (answer.equals("5")) {
            new MultiplayerInfoGui(gui, "Die Zeit ist leider um!");
        }
        else {
            new MultiplayerInfoGui(gui, "Bitte warte während die anderen Spieler deren Antworten abgeben...");
        }
    }

    private void stopTimer() {
        thread.stop();
    }
}

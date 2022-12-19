package de.hebk.gui.multiplayer;

import de.hebk.Question;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;
import de.hebk.utils.Joker;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerQuestionGui {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel questionLabel;
    private JButton telefonjokerButton;
    private JButton publikumsJokerButton;
    private JButton a5050JokerButton;

    public MultiplayerQuestionGui(StartGui gui, Client client, Question question, Joker[] joker) {
        questionLabel.setText(question.getBody());
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);

        if (joker[0].isUsed()) {
            telefonjokerButton.setBackground(Color.GRAY);
        }
        if (joker[1].isUsed()) {
            publikumsJokerButton.setBackground(Color.GRAY);
        }
        if (joker[2].isUsed()) {
            a5050JokerButton.setBackground(Color.GRAY);
        }

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        String info = "Bitte warte während die anderen Spieler deren Antworten abgeben...";
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.ANSWER, "1");
                client.send(packet);
                new MultiplayerInfoGui(gui, info);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.ANSWER, "2");
                client.send(packet);
                new MultiplayerInfoGui(gui, info);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.ANSWER, "3");
                client.send(packet);
                new MultiplayerInfoGui(gui, info);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Packet packet = new Packet(PacketType.ANSWER, "4");
                client.send(packet);
                new MultiplayerInfoGui(gui, info);
            }
        });

        telefonjokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> answers = joker[0].use(question);
                answers.toFirst();

                if (!button1.getText().equals(answers.getObject())) {
                    button1.setText("");
                }
                if (!button2.getText().equals(answers.getObject())) {
                    button2.setText("");
                }
                if (!button3.getText().equals(answers.getObject())) {
                    button3.setText("");
                }
                if (!button4.getText().equals(answers.getObject())) {
                    button4.setText("");
                }
            }
        });

        publikumsJokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> answers = joker[2].use(question);
                answers.toFirst();

                if (!button1.getText().equals(answers.getObject())) {
                    button1.setText("");
                }
                if (!button2.getText().equals(answers.getObject())) {
                    button2.setText("");
                }
                if (!button3.getText().equals(answers.getObject())) {
                    button3.setText("");
                }
                if (!button4.getText().equals(answers.getObject())) {
                    button4.setText("");
                }
            }
        });

        a5050JokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> answers = joker[1].use(question);
                answers.toFirst();

                for (int i = 0; i < answers.size(); i++) {
                    if (!button1.getText().equals(answers.getObject())) {
                        button1.setText("");
                    }
                    else if (!button2.getText().equals(answers.getObject())) {
                        button2.setText("");
                    }
                    else if (!button3.getText().equals(answers.getObject())) {
                        button3.setText("");
                    }
                    else if (!button4.getText().equals(answers.getObject())) {
                        button4.setText("");
                    }
                }
            }
        });
    }

    private void createUIComponents() {
        questionLabel = new JLabel("test");
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
    }
}

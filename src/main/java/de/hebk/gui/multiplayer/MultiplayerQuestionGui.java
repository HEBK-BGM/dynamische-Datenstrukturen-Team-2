package de.hebk.gui.multiplayer;

import de.hebk.Question;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import javax.swing.*;
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

    public MultiplayerQuestionGui(StartGui gui, Client client, Question question) {
        questionLabel.setText(question.getBody());
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);
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
    }

    private void createUIComponents() {
        questionLabel = new JLabel("test");
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
    }
}

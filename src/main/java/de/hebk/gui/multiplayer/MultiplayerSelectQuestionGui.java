package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.multiplayer.Client;
import de.hebk.multiplayer.Packet;
import de.hebk.multiplayer.PacketType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerSelectQuestionGui {
    private StartGui frame;
    private Client client;
    private JPanel panel1;
    private JButton button1;
    private JButton button3;
    private JButton button4;
    private JButton button2;

    /**
     * Creates a gui so the player can choose a question
     * @param gui       The frame
     * @param client    The client
     * @param questions The qeustion
     */
    public MultiplayerSelectQuestionGui(StartGui gui, Client client, String[] questions) {
        this.frame = gui;
        this.client = client;

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);

        frame.setContentPane(p);
        frame.revalidate();
        frame.repaint();

        button1.setText(questions[0]);
        button2.setText(questions[1]);
        button3.setText(questions[2]);
        button4.setText(questions[3]);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion(button1.getText());
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion(button2.getText());
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion(button3.getText());
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendQuestion(button4.getText());
            }
        });
    }

    /**
     * Sends the question to the server
     * @param question
     */
    private void sendQuestion(String question) {
        Packet packet = new Packet(PacketType.QUESTION_IS_SELECTED, question);
        client.send(packet);
    }
}
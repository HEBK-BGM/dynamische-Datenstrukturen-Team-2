package de.hebk.gui.multiplayer;

import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerGui {
    private StartGui frame;
    private JButton spielBeitretenButton;
    private JPanel panel1;
    private JButton spielErstellenButton;
    private JButton zurueckButton;

    public MultiplayerGui(StartGui gui) {
        this.frame = gui;
        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setContentPane(gui.getPanel());
                gui.revalidate();
                gui.repaint();
            }
        });

        spielBeitretenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerJoinGui(frame);
            }
        });

        spielErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerCreateGui(frame);
            }
        });
    }

    private JPanel getPanel() {
        return panel1;
    }
}

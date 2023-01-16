package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerGui {
    private StartGui frame;
    private JButton spielBeitretenButton;
    private JPanel panel1;
    private JButton spielErstellenButton;
    private JButton zurueckButton;

    /**
     * Contructor for a gui
     * @param gui   The frame
     */
    public MultiplayerGui(StartGui gui) {
        this.frame = gui;

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        gui.pack();

        gui.setContentPane(p);
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

    /**
     * Gets the panel
     * @return  The panel
     */
    private JPanel getPanel() {
        return panel1;
    }
}

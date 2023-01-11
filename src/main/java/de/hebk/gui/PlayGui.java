package de.hebk.gui;

import de.hebk.game.Config;
import de.hebk.gamemodes.Hardcore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGui {
    private JButton hardcoreButton;
    private JPanel panel1;
    private JButton normalButton;
    private JButton trueOrNotButton;
    private JButton zurueckButton;

    public PlayGui(StartGui gui) {
        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        gui.pack();

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();

        hardcoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hardcore(gui);
            }
        });

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setContentPane(gui.getPanel());
                gui.revalidate();
                gui.repaint();
            }
        });
    }
}

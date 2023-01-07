package de.hebk.gui;

import de.hebk.gamemodes.Hardcore;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGui {
    private JButton hardcoreButton;
    private JPanel panel1;
    private JButton normalButton;
    private JButton trueOrNotButton;
    private JButton zurueckButton;

    public PlayGui(StartGui gui) {
        gui.setContentPane(panel1);
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

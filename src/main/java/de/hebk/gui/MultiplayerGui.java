package de.hebk.gui;

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
        frame.add(panel1);
        frame.setVisible(true);

        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(panel1);
                frame.add(frame.getPanel());
                frame.repaint();
                frame.setVisible(true);
            }
        });
    }
}

package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerEndGui {
    private JPanel panel1;
    private JButton backToMainMenuButton;
    private JLabel infoLabel;

    public MultiplayerEndGui(StartGui gui, String info) {
        infoLabel.setText(info);

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        gui.pack();

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();

        backToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setContentPane(gui.getPanel());
                gui.revalidate();
                gui.repaint();
            }
        });
    }

    private void createUIComponents() {
        infoLabel = new JLabel();
    }
}
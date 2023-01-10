package de.hebk.gui;

import de.hebk.game.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGui {
    private JPanel panel1;
    private JButton backToMainMenuButton;
    private JLabel infoLabel;
    private JTextField namensfeld;
    private JLabel namenInfo;

    public EndGui(StartGui gui, String info, String gamemode, int lvl) {
        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(),new GridLayout());
        p.add(panel1);
        gui.pack();

        infoLabel.setText(info);
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
        namenInfo = new JLabel();
    }
}
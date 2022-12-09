package de.hebk.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGui extends JFrame {
    private JPanel panel1;
    private JButton highscoreButton;
    private JButton multiplayerButton;
    private JButton spielenButton;
    private JButton beendenButton;

    public StartGui() {
        super("Wer wird Millionär");

        this.add(panel1);
        this.setSize(960, 540);
        this.setVisible(true);

        /*highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel1);
                add(new HighscoreGui().getPanel());
                setVisible(true);
            }
        });*/

        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(panel1);
                MultiplayerGui multiplayerGui = new MultiplayerGui(StartGui.this);
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }
}

package de.hebk.gui;

import de.hebk.game.Config;
import de.hebk.gui.multiplayer.MultiplayerGui;
import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class StartGui extends JFrame {
    private JImagePanel imagePanel;
    private JPanel panel1;
    private JButton highscoreButton;
    private JButton multiplayerButton;
    private JButton spielenButton;
    private JButton beendenButton;

    public StartGui() {
        super("Wer wird Millionär");

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        setContentPane(p);
        pack();

        ImageIcon icon = new ImageIcon(Config.getAppIcon());
        this.setIconImage(icon.getImage());

        this.imagePanel = p;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(960, 540);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        highscoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HighscoreGui(StartGui.this);
            }
        });

        beendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MultiplayerGui(StartGui.this);
            }
        });

        spielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PlayGui(StartGui.this);
            }
        });
    }

    public JPanel getPanel() {
        return this.imagePanel;
    }
}
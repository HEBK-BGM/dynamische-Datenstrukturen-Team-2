package de.hebk.gui.normal;

import de.hebk.game.Config;
import de.hebk.game.Joker;
import de.hebk.game.Question;
import de.hebk.gamemodes.Normal;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.sound.SoundManager;

import javax.swing.*;
import java.awt.*;


public class NormalQuestionGUI {
    private StartGui startGui;
    private SoundManager soundManager;
    private Normal normal;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel questionLabel;
    private JButton telefonjokerButton;
    private JButton publikumsJokerButton;
    private JButton a5050JokerButton;

//, Question question, Joker[] joker, Normal normal
    public NormalQuestionGUI(StartGui startGui, SoundManager soundManager) {
        this.startGui = startGui;
        this.soundManager = soundManager;

        questionLabel = new JLabel("test");
        button1 = new JButton();
        button1.setText("hello world");


        //JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        //p.add(panel1);

        //startGui.setContentPane(p);

        startGui.revalidate();
        startGui.repaint();
    }
}

package de.hebk.gui;

import javax.swing.*;

public class HighscoreGui {
    private JPanel rootPanel;
    private JLabel Headline;
    private JTable highscoretable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("HighscoreGui");
        frame.setContentPane(new HighscoreGui().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.*;

public class MultiplayerQuestionIsSelectedGui {
    private JPanel panel1;
    private JLabel label;

    public MultiplayerQuestionIsSelectedGui(StartGui gui, String name) {
        label.setText("Bitte warte während " + name + " eine Frage auswählt...");

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();
    }

    private void createUIComponents() {
        label = new JLabel("");
    }
}
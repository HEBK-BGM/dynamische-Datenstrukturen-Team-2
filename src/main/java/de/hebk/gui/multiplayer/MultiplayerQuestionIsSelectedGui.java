package de.hebk.gui.multiplayer;

import de.hebk.gui.StartGui;

import javax.swing.*;

public class MultiplayerQuestionIsSelectedGui {
    private JPanel panel1;
    private JLabel label;

    public MultiplayerQuestionIsSelectedGui(StartGui gui, String name) {
        label.setText("Bitte warte während " + name + " eine Frage auswählt...");
        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();
    }

    private void createUIComponents() {
        label = new JLabel("");
    }
}
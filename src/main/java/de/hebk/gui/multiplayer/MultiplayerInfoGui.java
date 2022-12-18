package de.hebk.gui.multiplayer;

import de.hebk.gui.StartGui;

import javax.swing.*;

public class MultiplayerInfoGui {
    private JPanel panel1;
    private JLabel infoLabel;

    public MultiplayerInfoGui(StartGui gui, String info) {
        infoLabel.setText(info);
        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();
    }

    private void createUIComponents() {
        infoLabel = new JLabel("");
    }
}

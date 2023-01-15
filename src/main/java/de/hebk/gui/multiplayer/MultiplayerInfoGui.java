package de.hebk.gui.multiplayer;

import de.hebk.game.Config;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.*;

public class MultiplayerInfoGui {
    private JPanel panel1;
    private JLabel infoLabel;

    /**
     * Displays an info
     * @param gui   The frame
     * @param info  The info
     */
    public MultiplayerInfoGui(StartGui gui, String info) {
        infoLabel.setText(info);

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        gui.pack();

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();
    }

    private void createUIComponents() {
        infoLabel = new JLabel("");
    }
}

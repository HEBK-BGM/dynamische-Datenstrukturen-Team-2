package de.hebk.gui.trueOrNot;

import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Win {
    private JPanel panel1;
    private JLabel congrats;
    private JButton backtomenue;
    private JButton beendenButton;

    public Win(StartGui gui){
        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        beendenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        backtomenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartGui haupt = new StartGui();
            }
        });
    }
    private void createUIComponents() {
        congrats = new JLabel();
    }
}

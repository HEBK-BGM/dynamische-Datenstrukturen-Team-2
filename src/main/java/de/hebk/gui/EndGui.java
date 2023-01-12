package de.hebk.gui;

import de.hebk.game.Config;
import de.hebk.game.Highscore;
import de.hebk.game.SQLManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class EndGui {
    private JPanel panel1;
    private JButton backToMainMenuButton;
    private JLabel infoLabel;
    private JTextField namensfeld;
    private JLabel namenInfo;
    private JButton acceptButton;
    private JLabel saveLabel;

    public EndGui(StartGui gui, String info, String gamemode, int lvl, SQLManager sqlManager) {
        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(),new GridLayout());
        p.add(panel1);
        gui.pack();

        saveLabel.setText("");

        infoLabel.setText(info);
        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!namensfeld.getText().equals("")) {
                    Calendar cal = Calendar.getInstance();
                    String date = cal.get(Calendar.DAY_OF_MONTH) + "." + (cal.getTime().getMonth()+1) + "." + cal.get(Calendar.YEAR) + " - " + cal.getTime().getHours() + ":" + cal.getTime().getMinutes();

                    Highscore highscore = new Highscore(namensfeld.getText(), gamemode, lvl, Config.hardcoreLevelToMoney(lvl), date);
                    sqlManager.addHighscore(highscore);

                    saveLabel.setText("Highscore gespeichert...");
                }
                else {
                    saveLabel.setText("Bitte gebe deinen Namen ein!");
                }
            }
        });

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
        saveLabel = new JLabel("");
    }
}
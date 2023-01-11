package de.hebk.gui;

import de.hebk.game.Highscore;
import de.hebk.game.SQLManager;
import de.hebk.game.Config;
import de.hebk.model.stack.Stack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighscoreGui {
    private JPanel panel1;
    private JLabel headline;
    private JTable table1;
    private JButton zurueckButton;
    private JScrollBar scrollBar1;

    public HighscoreGui(StartGui gui) {
        zurueckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setContentPane(gui.getPanel());
                gui.revalidate();
                gui.repaint();
            }
        });

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();
    }

    private void createUIComponents() {
        SQLManager sqlManager = new SQLManager(Config.getDatabaseURL());
        Stack<Highscore> highscores = sqlManager.getHighscores();
        int size = highscores.size();

        String[] columnNames = {"Name", "Spielmodus", "Level", "Geld", "Datum"};
        Object[][] data = new Object[size][5];

        for (int i = 0; i < size; i++) {
            Highscore h = highscores.pop();

            data[i][0] = h.getName();
            data[i][1] = h.getGamemode();
            data[i][2] = h.getLevel();
            data[i][3] = h.getMoney();
            data[i][4] = h.getDate();
        }

        TableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1 = new JTable(model);
    }
}
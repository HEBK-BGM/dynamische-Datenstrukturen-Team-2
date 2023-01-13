package de.hebk.gui.trueOrNot;

import de.hebk.game.Config;
import de.hebk.gamemodes.TrueOrNot;
import de.hebk.gui.EndGui;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrueOrNotStart {

    TrueOrNot trueOrNotClone;
    private boolean trueOrFalse ;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel question;
    private JLabel money;

    private StartGui startGui;
    private int lvl = 0;




    public TrueOrNotStart(StartGui gui) {
        startGui=gui;

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        gui.pack();

        gui.setContentPane(p);
        gui.revalidate();
        gui.repaint();

        TrueOrNot trueOrNot = new TrueOrNot();
        this.trueOrNotClone=trueOrNot;
        question.setText(trueOrNotClone.createQuestion());
        money.setText(String.valueOf(trueOrNotClone.getMoney()));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trueOrFalse = trueOrNotClone.checkCorrect(true);
                winOrLose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trueOrFalse = trueOrNotClone.checkCorrect(false);
                winOrLose();
            }
        });

    }

    private void createUIComponents() {
        question = new JLabel();
        money = new JLabel();
    }


    public void winOrLose(){
        if(trueOrFalse){
            win();
        }
        if(!trueOrFalse){
            lose();
        }
    }
    public void win(){
        trueOrNotClone.setMoney(trueOrNotClone.getMoney());
        trueOrNotClone.deletequestion();
        trueOrNotClone.createQuestion();
        question.setText(trueOrNotClone.createQuestion());
        money.setText(String.valueOf(trueOrNotClone.getMoney()));
        startGui.revalidate();
        startGui.repaint();
        lvl++;

        if(trueOrNotClone.getMoney()==1000000){
            finalwin();
        }

    }
    public void lose(){
        String info = "Du hast verloren!";
        new EndGui(startGui,info,"True Or Not",lvl,trueOrNotClone.getSql1());
    }

    public void finalwin(){
        String info="Du hast den Modus True Or Not besiegt und 1.000.000€ gewonnen!";
        new EndGui(startGui,info,"True Or Not",30,trueOrNotClone.getSql1());
    }

    public TrueOrNot getTrueOrNotClone() {
        return trueOrNotClone;
    }
}



package de.hebk.gui.trueOrNot;

import de.hebk.gamemodes.TrueOrNot;
import de.hebk.gui.StartGui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrueOrNotStart {

    TrueOrNot trueOrNot;
    private boolean trueOrFalse;
    private boolean answer;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel question;
    private JLabel money;

    private StartGui startGui;




    public TrueOrNotStart(StartGui gui, TrueOrNot statement) {
        startGui=gui;
        trueOrNot=statement;
        question.setText(statement.createQuestion());
        money.setText(String.valueOf(trueOrNot.getMoney()));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer(true);
                trueOrFalse = statement.checkCorrect();
                winOrLose();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer(false);
                trueOrFalse = statement.checkCorrect();
                winOrLose();
            }
        });

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

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
        trueOrNot.setMoney(trueOrNot.getMoney());
        trueOrNot.createQuestion();
        if(trueOrNot.getMoney()==1000000){
            finalwin();
        }

    }
    public void lose(){
        new Lose(startGui, trueOrNot.getMoney());
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void finalwin(){
        new Win(startGui);
    }

    }



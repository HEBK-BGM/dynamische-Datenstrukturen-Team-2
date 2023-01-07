package de.hebk.gui;

import de.hebk.Question;
import de.hebk.game.Joker;

import de.hebk.gamemodes.Hardcore;
import de.hebk.model.list.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardcoreGui {
    private Hardcore hardcore;

    private JPanel panel1;
    private JPanel answeres;
    private JPanel joker;
    private JPanel value;
    private JPanel question;
    private JButton telefon;
    private JButton fiftyfifty;
    private JButton publikum;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel frage;
    private JLabel geld;

    public HardcoreGui(StartGui gui, Hardcore hardcore, Question question, Joker[] joker) {
        this.hardcore = hardcore;

        frage.setText(question.getBody());
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);

        gui.setContentPane(panel1);
        gui.revalidate();
        gui.repaint();

        if (joker[0].isUsed()) {
            telefon.setBackground(Color.GRAY);
        }
        if (joker[1].isUsed()) {
            publikum.setBackground(Color.GRAY);
        }
        if (joker[2].isUsed()) {
            fiftyfifty.setBackground(Color.GRAY);
        }

        telefon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!telefon.getBackground().equals(Color.GRAY)) {
                    List<String> answer = joker[0].use(question);
                    answer.toFirst();

                    if (button1.getText().equals(answer.getObject())) {
                        button1.setBackground(Color.GREEN);
                    }
                    if (button2.getText().equals(answer.getObject())) {
                        button2.setBackground(Color.GREEN);
                    }
                    if (button3.getText().equals(answer.getObject())) {
                        button3.setBackground(Color.GREEN);
                    }
                    if (button4.getText().equals(answer.getObject())) {
                        button4.setBackground(Color.GREEN);
                    }
                    telefon.setBackground(Color.GRAY);
                }
            }
        });

        publikum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!publikum.getBackground().equals(Color.GRAY)) {
                    List<String> answer = joker[0].use(question);
                    answer.toFirst();

                    if (button1.getText().equals(answer.getObject())) {
                        button1.setBackground(Color.GREEN);
                    }
                    if (button2.getText().equals(answer.getObject())) {
                        button2.setBackground(Color.GREEN);
                    }
                    if (button3.getText().equals(answer.getObject())) {
                        button3.setBackground(Color.GREEN);
                    }
                    if (button4.getText().equals(answer.getObject())) {
                        button4.setBackground(Color.GREEN);
                    }
                    publikum.setBackground(Color.GRAY);
                }
            }
        });

        fiftyfifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!fiftyfifty.getBackground().equals(Color.GRAY)) {
                    List<String> answer = joker[2].use(question);
                    answer.toFirst();

                    for (int i = 0; i < answer.size(); i++) {
                        if (button1.getText().equals(answer.getObject())) {
                            button1.setText(" ");
                        }
                        if (button2.getText().equals(answer.getObject())) {
                            button2.setText(" ");
                        }
                        if (button3.getText().equals(answer.getObject())) {
                            button3.setText(" ");
                        }
                        if (button4.getText().equals(answer.getObject())) {
                            button4.setText(" ");
                        }
                        answer.next();
                    }
                    fiftyfifty.setBackground(Color.GRAY);
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(question, question.getAnswers()[0]);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(question, question.getAnswers()[1]);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(question, question.getAnswers()[2]);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(question, question.getAnswers()[3]);
            }
        });
    }

    private void createUIComponents() {
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        frage = new JLabel();
    }

    private void checkAnswer(Question question, String answer) {
        if (question.getCorrectAnswer().equals(question.getAnswers()[0])) {
            hardcore.nextQuestion();
        }
        else {
            hardcore.stopGame();
        }
    }
}

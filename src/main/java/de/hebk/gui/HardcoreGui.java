package de.hebk.gui;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.game.Joker;

import de.hebk.gamemodes.Hardcore;
import de.hebk.model.list.List;
import de.hebk.sound.SoundManager;
import de.hebk.sound.SoundType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HardcoreGui {
    private Thread thread;
    private Hardcore hardcore;
    private SoundManager soundManager;
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
    private JLabel timer;

    /**
     * Construcor for the harcore gui
     * @param gui           The frame
     * @param soundManager  The sound manager
     * @param hardcore      The hardcore controller
     * @param question      The question
     * @param joker         The joker
     * @param lvl           The level
     */
    public HardcoreGui(StartGui gui, SoundManager soundManager, Hardcore hardcore, Question question, Joker[] joker, int lvl) {
        this.hardcore = hardcore;
        this.soundManager = soundManager;

        geld.setText(Config.hardcoreLevelToMoney(lvl) + " €");

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =60; i>=0; i--){
                    try {
                        timer.setText("Zeit: " + i + " Sekunden");

                        Thread.sleep(1000);

                        if(i==0){
                            hardcore.stopGame();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread.start();

        frage.setText(question.getBody());
        button1.setText(question.getAnswers()[0]);
        button2.setText(question.getAnswers()[1]);
        button3.setText(question.getAnswers()[2]);
        button4.setText(question.getAnswers()[3]);

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);

        gui.setContentPane(p);
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
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.TELEPHONE_JOKER, true);
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
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.AUDIENCE_JOKER, true);
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
                    soundManager.stopSound();
                    soundManager.playSound(SoundType.HALF_JOKER, false);
                    soundManager.playNext(SoundType.QUESTION, true);
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
        geld = new JLabel();

        timer = new JLabel();
    }

    /**
     * Checks if the answer is true or not
     * @param question
     * @param answer
     */
    private void checkAnswer(Question question, String answer) {
        thread.stop();

        if (question.getCorrectAnswer().equals(question.getAnswers()[0])) {
            soundManager.stopSound();
            soundManager.playSound(SoundType.RIGHT_ANSWER, false);
            soundManager.playNext(SoundType.QUESTION, true);
            hardcore.nextQuestion();
        }
        else {
            soundManager.stopSound();
            soundManager.playSound(SoundType.WRONG_ANSWER, false);
            hardcore.stopGame();
        }
    }
}

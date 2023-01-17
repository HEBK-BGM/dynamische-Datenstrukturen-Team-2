package de.hebk.gui.normal;

import de.hebk.game.Config;
import de.hebk.game.Joker;
import de.hebk.game.Question;
import de.hebk.gamemodes.Normal;
import de.hebk.gui.JImagePanel;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;
import de.hebk.sound.SoundManager;
import de.hebk.sound.SoundType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NormalQuestionGUI {
    private StartGui startGui;
    private SoundManager soundManager;
    private Normal normal;
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel questionLabel;
    private JButton telefonjokerButton;
    private JButton publikumsJokerButton;
    private JButton a5050JokerButton;
    private JLabel stagelabel;
    private JLabel moneylabel;
    private JPanel left;
    private JPanel right;
    private JPanel bottom;
    private JPanel middle;

    /**
     * Constructor for the NormalQuestionGUI
     * @param startGui
     * @param normal
     * @param soundManager
     */
    public NormalQuestionGUI(StartGui startGui,Normal normal, SoundManager soundManager, Joker[] joker) {
        this.startGui = startGui;
        this.normal = normal;
        this.soundManager = soundManager;

        JImagePanel p = new JImagePanel(new ImageIcon(Config.getBackground()).getImage(), new GridLayout());
        p.add(panel1);
        startGui.pack();

        //beschriften
        questionLabel.setText(normal.getQuestion().getBody());
        moneylabel.setText(String.valueOf(normal.getMoney()));
        stagelabel.setText(String.valueOf(normal.getStufe()));
        button1.setText(normal.getQuestion().getAnswers()[0]);
        button2.setText(normal.getQuestion().getAnswers()[1]);
        button3.setText(normal.getQuestion().getAnswers()[2]);
        button4.setText(normal.getQuestion().getAnswers()[3]);

        startGui.setContentPane(p);
        startGui.revalidate();
        startGui.repaint();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normal.checkanswer(1);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normal.checkanswer(2);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normal.checkanswer(3);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normal.checkanswer(4);
            }
        });

        loadJoker(joker, normal.getQuestion());
    }

    /**
     * Loads the joker
     * @param joker
     * @param question
     */
    private void loadJoker(Joker[] joker, Question question) {
        if (joker[0].isUsed()) {
            telefonjokerButton.setBackground(Color.GRAY);
        }
        if (joker[1].isUsed()) {
            publikumsJokerButton.setBackground(Color.GRAY);
        }
        if (joker[2].isUsed()) {
            a5050JokerButton.setBackground(Color.GRAY);
        }

        telefonjokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!telefonjokerButton.getBackground().equals(Color.GRAY)) {
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
                    telefonjokerButton.setBackground(Color.GRAY);
                }
            }
        });

        publikumsJokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!publikumsJokerButton.getBackground().equals(Color.GRAY)) {
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
                    publikumsJokerButton.setBackground(Color.GRAY);
                }
            }
        });

        a5050JokerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!a5050JokerButton.getBackground().equals(Color.GRAY)) {
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
                    a5050JokerButton.setBackground(Color.GRAY);
                }
            }
        });
    }

    /**
     *
     */
    private void createUIComponents() {
        questionLabel = new JLabel("test");
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
    }
}

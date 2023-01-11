package de.hebk;

import de.hebk.game.Config;
import de.hebk.gamemodes.Normal;
import de.hebk.gui.StartGui;
import de.hebk.gui.normal.NormalQuestionGUI;
import javafx.embed.swing.JFXPanel;

public class Main {
    static {
        JFXPanel panel = new JFXPanel();
    }

    public static void main(String[] args) {
        /*
        if (args.length > 0 && args[0].equals("--enable-cheats")) {
            Config.setCheating(true);
        }
        */
        StartGui gui = new StartGui();

        //NormalQuestionGUI normalQuestionGUI = new NormalQuestionGUI();
    }
}
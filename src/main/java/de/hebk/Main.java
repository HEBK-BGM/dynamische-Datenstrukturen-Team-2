package de.hebk;

import de.hebk.game.Config;
import de.hebk.game.Question;
import de.hebk.gui.StartGui;
import de.hebk.model.list.List;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--enable-cheats")) {
            Config.setCheating(true);
        }

        StartGui gui = new StartGui();
    }
}
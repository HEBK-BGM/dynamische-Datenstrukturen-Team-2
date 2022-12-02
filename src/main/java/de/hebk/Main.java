package de.hebk;

import de.hebk.gui.Menu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu(stage);
        menu.mainMenu();
    }
}
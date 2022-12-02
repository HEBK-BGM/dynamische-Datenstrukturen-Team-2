package de.hebk.gui;

import de.hebk.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    private FXMLLoader fxmlLoader;
    private Stage stage;


    public Menu(Stage stage) {
        this.stage = stage;
    }

    public void mainMenu() throws IOException {
        fxmlLoader = new FXMLLoader(Main.class.getResource("main_menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
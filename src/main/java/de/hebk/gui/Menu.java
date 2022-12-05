package de.hebk.gui;

import de.hebk.Main;
import de.hebk.media.sound.SoundManager;
import de.hebk.media.sound.SoundType;
import de.hebk.media.video.VideoManager;
import de.hebk.media.video.VideoType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Menu {
    private FXMLLoader fxmlLoader;
    private Stage stage;


    public Menu(Stage stage) {
        this.stage = stage;
    }

    public void mainMenu() throws IOException {
        VideoManager videoManager = new VideoManager(stage);
        videoManager.playVideo(VideoType.INTRO);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // TODO: Add Main Menu
            }
        }));
        timeline.play();
    }
}
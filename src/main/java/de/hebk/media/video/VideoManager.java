package de.hebk.media.video;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class VideoManager {
    private MediaPlayer player;
    private Stage stage;

    public VideoManager(Stage stage) {
        this.stage = stage;
    }

    public Duration playVideo(VideoType type) {
        String path = "src\\main\\java\\de\\hebk\\assets\\video\\";

        switch (type) {
            case INTRO -> path += "intro_2.mp4";
        }

        Media media = new Media(new File(path).toURI().toString());
        player = new MediaPlayer(media);
        MediaView mediaView = new MediaView(player);

        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();
        player.play();

        return media.getDuration();
    }

    public void stopVideo() {
        player.stop();
    }
}
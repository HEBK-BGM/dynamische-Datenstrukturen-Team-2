package de.hebk.media.sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundManager {
    private MediaPlayer player;

    public void playSound(SoundType type) {
        String path = "src\\main\\java\\de\\hebk\\assets\\sound\\";

        switch (type) {
            case QUESTION -> path += "question.wav";
            case RIGHT_ANSWER -> path += "right_answer.wav";
            case WRONG_ANSWER -> path += "wrong_answer.wav";
            case HALF_JOKER -> path += "half_joker.wav";
            case TELEPHONE_JOKER -> path += "telephone_joker.wav";
            case WIN -> path += "win.wav";
            case AUDIENCE_JOKER -> path += "audience_joker.wav";
            case START -> path += "start.wav";
        }

        Media media = new Media(new File(path).toURI().toString());
        player = new MediaPlayer(media);
        player.play();
    }

    public void stopSound() {
        player.stop();
    }
}
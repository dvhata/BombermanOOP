package uet.oop.bomberman.entities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sound {

    public void playSound (String nameSoundFile) {
        MediaPlayer mediaPlayer;
        String s = nameSoundFile;
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }
}
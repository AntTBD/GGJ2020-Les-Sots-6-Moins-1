package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Text;
import javafx.util.Duration;
import mains.Main;
import mains.SceneLoader;
import java.io.File;
import java.io.IOException;

public class HomeController {
    public MediaPlayer mediaPlayerMenu;
    public Text BoutonRegles;

    @FXML
    private void change() throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
        mediaPlayerMenu.stop();
    }

    @FXML
    private void quitter() {
        System.exit(0);
    }

    @FXML
    public void initialize(){
        String pathMenu = "src/sons/musique_menu.mp3";
        Media mediaMenu = new Media(new File(pathMenu).toURI().toString());
        mediaPlayerMenu = new MediaPlayer(mediaMenu);
        mediaPlayerMenu.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayerMenu.seek(Duration.ZERO);
            }
        });
        mediaPlayerMenu.play();
    }

    @FXML
    public void VoirRegles(MouseEvent mouseEvent) throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_INSTRUCTIONS);
    }
}

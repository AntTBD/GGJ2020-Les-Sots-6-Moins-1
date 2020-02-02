package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mains.Main;
import mains.SceneLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class FinController {
    public static int score = 0;
    public Text texte_score;
    public ImageView eau;
    private Timeline tl;
    private MediaPlayer mediaPlayerFin;
    private int index_tl = 0;
    private static ArrayList<Image> img_eau = new ArrayList<>(Arrays.asList(new Image("images/waterfall/W1001.png"),new Image("images/waterfall/W1002.png"),new Image("images/waterfall/W1003.png")
            ,new Image("images/waterfall/W1004.png")
            ,new Image("images/waterfall/W1005.png")
            ,new Image("images/waterfall/W1006.png")
            ,new Image("images/waterfall/W1007.png")
            ,new Image("images/waterfall/W1008.png")
    ));
    public ImageView perso;
    private Timeline tl_perso;
    private int index_tl_perso = 0;
    private static ArrayList<Image> img_perso = new ArrayList<>(Arrays.asList(
            new Image("images/perso/etoile/images/perso_etoile_01.png")
            ,new Image("images/perso/etoile/images/perso_etoile_02.png")
            ,new Image("images/perso/etoile/images/perso_etoile_03.png")
    ));
    public void initialize() {

        String pathFin = "src/sons/Musique fin.mp3";
        Media mediaFin = new Media(new File(pathFin).toURI().toString());
        mediaPlayerFin = new MediaPlayer(mediaFin);
        mediaPlayerFin.setOnEndOfMedia(() -> mediaPlayerFin.seek(Duration.ZERO));
        mediaPlayerFin.play();

        texte_score.setText("Score : " + score);

        tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);

        tl.getKeyFrames().add(0, new KeyFrame(Duration.millis(50), e -> {
            eau.setImage(img_eau.get((index_tl++) % img_eau.size()));
        }));
        tl.play();

        tl_perso = new Timeline();
        tl_perso.setCycleCount(Animation.INDEFINITE);
        tl_perso.getKeyFrames().add(0, new KeyFrame(Duration.millis(150), e -> {
            perso.setImage(img_perso.get((index_tl_perso++) % img_perso.size()));
        }));
        tl_perso.play();
    }


    public void rejouer(MouseEvent mouseEvent) throws IOException {
        mediaPlayerFin.stop();
        Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
    }

    public void goToMenu(MouseEvent mouseEvent) throws IOException{
        mediaPlayerFin.stop();
        Main.sceneLoader.switchTo(SceneLoader.SCENE_HOME);
    }
}

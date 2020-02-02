package controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class FinController {
    public ImageView eau;
    private Timeline tl;
    private int index_tl;
    private static ArrayList<Image> img_eau = new ArrayList<>(Arrays.asList(new Image("images/waterfall/W1001.png"),new Image("images/waterfall/W1002.png"),new Image("images/waterfall/W1003.png")
            ,new Image("images/waterfall/W1004.png")
            ,new Image("images/waterfall/W1005.png")
            ,new Image("images/waterfall/W1006.png")
            ,new Image("images/waterfall/W1007.png")
            ,new Image("images/waterfall/W1008.png")
    ));

    public void initialize() {
        tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(0, new KeyFrame(Duration.millis(50), e -> {
            eau.setImage(img_eau.get((index_tl++) % img_eau.size()));
        }));
        tl.play();
    }
}

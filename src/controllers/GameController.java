package controllers;

import classes.Scheduler;
import classes.WaterFall;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class GameController {
    private static ArrayList<Image> spriteWaterfalls = new ArrayList<>(Arrays.asList(new Image("images/waterfall/W1001.png"),new Image("images/waterfall/W1002.png"),new Image("images/waterfall/W1003.png")
            ,new Image("images/waterfall/W1004.png")
            ,new Image("images/waterfall/W1005.png")
            ,new Image("images/waterfall/W1006.png")
            ,new Image("images/waterfall/W1007.png")
            ,new Image("images/waterfall/W1008.png")
    ));
    public ImageView tuyauxTopRight;
    public ImageView tuyauxTopLeft;
    public ImageView tuyauxMid;
    public ImageView tuyauxBotLeft;
    public ImageView tuyauxBotRight;
    public ImageView waterTopLeft;
    public ImageView waterMid;
    public ImageView waterTopRight;
    public ImageView waterBotLeft;
    public ImageView waterBotRight;
    public void initialize(){
        ArrayList<WaterFall> waterFalls = new ArrayList<>(Arrays.asList(new WaterFall(waterMid)
                ,new WaterFall(waterBotLeft),new WaterFall(waterBotRight)
                ,new WaterFall(waterTopLeft),new WaterFall(waterTopRight)));
        Scheduler scheduler = new Scheduler(waterFalls);
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(5000),event -> {
             scheduler.selectNextAndPlay(spriteWaterfalls);

        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();
    }
}

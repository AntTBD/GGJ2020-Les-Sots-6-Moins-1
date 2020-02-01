package controllers;

import classes.Scheduler;
import classes.Tuyau;
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
    public ImageView tuyauTopRight;
    public ImageView tuyauTopLeft;
    public ImageView tuyauMid;
    public ImageView tuyauBotRight;
    public ImageView waterTopLeft;
    public ImageView waterMid;
    public ImageView waterTopRight;
    public ImageView waterBotRight;
    public ImageView tuyauBotMid;
    public ImageView tuyauTopMid;
    public ImageView waterTopMid;
    public ImageView waterBotMid;
    public ImageView waterMidRight;
    public ImageView tuyauMidRight;

    public void initialize(){
        ArrayList<Tuyau> tuyaux = new ArrayList<>(Arrays.asList(
                new Tuyau(waterBotRight,tuyauBotRight),new Tuyau(waterTopRight,tuyauTopRight)
                ,new Tuyau(waterTopMid,tuyauTopMid),new Tuyau(waterMid,tuyauMid)
                ,new Tuyau(waterBotMid,tuyauBotMid),new Tuyau(waterMidRight,tuyauMidRight)
                ,new Tuyau(waterTopLeft,tuyauTopLeft)
        ));
        Scheduler scheduler = new Scheduler(tuyaux);
        tuyaux.forEach(tuyau -> tuyau.getImgWater().setOnMouseReleased(event ->scheduler.repare(tuyau) ));

        Timeline tlScheduler = new Timeline(new KeyFrame(Duration.millis(5000),event -> scheduler.selectNextAndPlay(spriteWaterfalls)));
        tlScheduler.setCycleCount(Animation.INDEFINITE);
        tlScheduler.play();
    }
}

package controllers;

import classes.Scheduler;
import classes.Tuyau;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
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
    public static Image imageBase = new Image("images/perso/sur_place/images/perso_sur_place_01.png");
    private int imageCourseIndex = 0;
    private int imageSautIndex = 0;
    private int imageGravIndex = 0;
    private int imageAttaqueIndex = 0;
    private int distance = 0;
    private int distanceSaut = 0;
    private int hauteurSaut = 30;
    private boolean enSaut = false;

    private Timeline tlCourse, tlSaut, tlBase,tlAttaque;


    public ImageView animation;

    private ArrayList<Image> spritesCourse = null;
    private ArrayList<Image> spritesSaut = null;
    private ArrayList<Image> spritesGrav = null;
    private ArrayList<Image> spritesAttaque = null;
    public void initialize() {
        ArrayList<Tuyau> tuyaux = new ArrayList<>(Arrays.asList(
                new Tuyau(waterBotRight, tuyauBotRight), new Tuyau(waterTopRight, tuyauTopRight)
                , new Tuyau(waterTopMid, tuyauTopMid), new Tuyau(waterMid, tuyauMid)
                , new Tuyau(waterBotMid, tuyauBotMid), new Tuyau(waterMidRight, tuyauMidRight)
                , new Tuyau(waterTopLeft, tuyauTopLeft)
        ));
        Scheduler scheduler = new Scheduler(tuyaux);
        tuyaux.forEach(tuyau -> tuyau.getImgWater().setOnMouseReleased(event -> scheduler.repare(tuyau)));

        Timeline tlScheduler = new Timeline(new KeyFrame(Duration.millis(5000), event -> scheduler.selectNextAndPlay(spriteWaterfalls)));
        tlScheduler.setCycleCount(Animation.INDEFINITE);
        tlScheduler.play();
        animation.setLayoutY(400);
        animation.setLayoutX(300);
        animation.setTranslateX(-animation.getFitWidth() / 2);
        animation.setTranslateY(-animation.getFitHeight());
        spritesCourse = new ArrayList<>();

        tlAttaque = new Timeline();
        spritesAttaque = new ArrayList<>();
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_01.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_02.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_03.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_03.png"));


        tlCourse = new Timeline();
        spritesCourse = new ArrayList<>();
        spritesCourse.add(new Image("images/perso/court/images/perso_court_01.png"));
        spritesCourse.add(new Image("images/perso/court/images/perso_court_02.png"));
        spritesCourse.add(new Image("images/perso/court/images/perso_court_03.png"));
        spritesCourse.add(new Image("images/perso/court/images/perso_court_04.png"));
        spritesCourse.add(new Image("images/perso/court/images/perso_court_05.png"));
        spritesCourse.add(new Image("images/perso/court/images/perso_court_06.png"));


        tlSaut = new Timeline();
        spritesSaut = new ArrayList<>();
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_01.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_02.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_02.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_03.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_04.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_05.png"));
        spritesSaut.add(new Image("images/perso/saute/images/perso_saute_06.png"));

        tlBase = new Timeline();
        spritesGrav = new ArrayList<>();
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_01.png"));
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_02.png"));
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_03.png"));
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_04.png"));
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_05.png"));
        spritesGrav.add(new Image("images/perso/sur_place/images/perso_sur_place_06.png"));

        tlAttaque.setCycleCount(Animation.INDEFINITE);
        tlAttaque.getKeyFrames().add(0, new KeyFrame(Duration.millis(100), e -> {
            animation.setImage(spritesAttaque.get((imageAttaqueIndex++) % spritesAttaque.size()));
            if (imageAttaqueIndex >= spritesAttaque.size()) {
                imageAttaqueIndex = 0;
                tlAttaque.stop();
            }
        }));

        tlBase.setCycleCount(Animation.INDEFINITE);
        tlBase.getKeyFrames().add(0, new KeyFrame(Duration.millis(100), e -> {
            if (tlCourse.getStatus() != Animation.Status.RUNNING && tlSaut.getStatus() != Animation.Status.RUNNING && tlAttaque.getStatus() != Animation.Status.RUNNING)
                animation.setImage(spritesGrav.get((imageGravIndex++) % spritesGrav.size()));
        }));
        tlBase.play();

        tlCourse.setCycleCount(Animation.INDEFINITE);
        tlCourse.getKeyFrames().add(0, new KeyFrame(Duration.millis(60), e -> {
            if (!enSaut) {
                /*if (animation.getLayoutY() < sol.getLayoutY()) {
                    System.out.println("oui");
                    if (animation.getLayoutY() == plateforme.getLayoutY()) {
                        System.out.println("ok");
                        if (animation.getLayoutX() >= plateforme.getLayoutX() + plateforme.getWidth() || animation.getLayoutX() <= plateforme.getLayoutX()) {
                            System.out.println(plateforme.getLayoutX());
                            imageSautIndex = 5;
                            tlSaut.play();
                            System.out.println(plateforme.getWidth());
                        }
                    }
                }*/
                if (tlAttaque.getStatus() != Animation.Status.RUNNING)
                    animation.setImage(spritesCourse.get((imageCourseIndex++) % spritesCourse.size()));
            }
            animation.setLayoutX(animation.getLayoutX() + distance);

        }));

        tlSaut.setCycleCount(Animation.INDEFINITE);
        tlSaut.getKeyFrames().add(0, new KeyFrame(Duration.millis(60), e -> {
            if (tlAttaque.getStatus() != Animation.Status.RUNNING) {
                animation.setImage(spritesSaut.get((imageSautIndex++) % spritesSaut.size()));
            } else imageSautIndex++;

            if (animation.getScaleX() == -1) {
                distanceSaut = 15;
            } else distanceSaut = -15;

            if (imageSautIndex < 5) {
                animation.setLayoutY(animation.getLayoutY() - hauteurSaut);
            } else {
                animation.setLayoutY(animation.getLayoutY() + hauteurSaut);

                if (animation.getLayoutY() >= 400) {
                    imageSautIndex = 0;
                    animation.setLayoutY(400);
                    animation.setImage(imageBase);
                    tlSaut.stop();
                    enSaut = false;
                } /*else if ((Math.abs(animation.getLayoutY() - plateforme.getLayoutY()) <= 10) && animation.getLayoutX() <= 700 && animation.getLayoutX() >= 500) {
                    imageSautIndex = 0;
                    animation.setLayoutY(plateforme.getLayoutY());
                    animation.setImage(imageBase);
                    tlSaut.stop();
                    enSaut = false;
                }*/

            }
            animation.setLayoutX(animation.getLayoutX() - distanceSaut);
        }));
    }
    public void deplacement(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.RIGHT) {
            distance = 20;
            animation.setScaleX(1);
            tlCourse.play();
        } else if(keyEvent.getCode() == KeyCode.LEFT) {
            distance = -20;
            animation.setScaleX(-1);
            tlCourse.play();
        } else if(keyEvent.getCode() == KeyCode.UP && tlSaut.getStatus() != Animation.Status.RUNNING) {
            enSaut = true;
            imageSautIndex = 0;
            tlSaut.play();
        } else if(keyEvent.getCode() == KeyCode.I) {
            System.out.println( "X : " + animation.getLayoutX() + " Y : " + animation.getLayoutY());
        } else if(keyEvent.getCode() == KeyCode.SPACE) {
            tlAttaque.play();
        }

    }

    public void halte(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.RIGHT) {
            animation.setImage(imageBase);
            tlCourse.stop();
        } else if(keyEvent.getCode() == KeyCode.LEFT) {
            animation.setImage(imageBase);
            tlCourse.stop();
        }
    }
}

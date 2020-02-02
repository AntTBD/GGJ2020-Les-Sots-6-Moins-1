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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import mains.Main;
import mains.SceneLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController {
    public static final int MAX_Y_JAUGE = 224;
    private static ArrayList<Image> spriteWaterfalls = new ArrayList<>(Arrays.asList(new Image("images/waterfall/W1001.png"), new Image("images/waterfall/W1002.png"), new Image("images/waterfall/W1003.png")
            , new Image("images/waterfall/W1004.png")
            , new Image("images/waterfall/W1005.png")
            , new Image("images/waterfall/W1006.png")
            , new Image("images/waterfall/W1007.png")
            , new Image("images/waterfall/W1008.png")
    ));
    private static ArrayList<Image> spriteEgoutFalls = new ArrayList<>(Arrays.asList(new Image("images/waterfall_egoux/images/waterfall_egoux_01.png"),
            new Image("images/waterfall_egoux/images/waterfall_egoux_02.png")
            ,new Image("images/waterfall_egoux/images/waterfall_egoux_03.png")
            ,new Image("images/waterfall_egoux/images/waterfall_egoux_04.png")
            ,new Image("images/waterfall_egoux/images/waterfall_egoux_05.png")
            ,new Image("images/waterfall_egoux/images/waterfall_egoux_06.png")
            ,new Image("images/waterfall_egoux/images/waterfall_egoux_07.png")));
    public ImageView plateforme0;
    public ImageView plateforme1;
    public ImageView plateforme2;
    public ImageView plateforme3;
    public ImageView plateforme4;
    public ImageView plateforme5;
    public ImageView plateforme6;
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
    public Rectangle jauge;
    public Text timer;
    public ImageView waterEgouts1;
    public ImageView waterEgouts2;
    public ImageView waterEgouts3;
    private int temps = 0;
    private int imageCourseIndex = 0;
    private int imageSautIndex = 0;
    private int imageGravIndex = 0;
    private int imageAttaqueIndex = 0;
    private int distance = 0;
    private int hauteurSaut = 45;
    private boolean enSaut = false;
    private MediaPlayer mediaPlayerJeu;
    private MediaPlayer mediaPlayerSaut;
    private MediaPlayer mediaPlayerRepare;
    private Timeline tlCourse, tlSaut, tlAttaque, tlScheduler, tlJauge, tltemps,tlEgouts;
    public ImageView animation;
    private Scheduler scheduler;
    private ArrayList<Image> spritesCourse = null;
    private ArrayList<Image> spritesSaut = null;
    private ArrayList<Image> spritesGrav = null;
    private ArrayList<Image> spritesAttaque = null;
    private ArrayList<Tuyau> tuyaux = new ArrayList<>();
    private ArrayList<ImageView> waters = new ArrayList<>();
    private ArrayList<ImageView> liste_plateforme = new ArrayList<>();
    private int compteur =0;
    public void initialize() {
        timer.setText("0");
        String pathJeu = "src/sons/Musique InGame.mp3";
        Media mediaJeu = new Media(new File(pathJeu).toURI().toString());
        mediaPlayerJeu = new MediaPlayer(mediaJeu);
        mediaPlayerJeu.setOnEndOfMedia(() -> mediaPlayerJeu.seek(Duration.ZERO));
        mediaPlayerJeu.play();

        tltemps = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> avancerTemps()));
        tltemps.setCycleCount(Animation.INDEFINITE);
        tltemps.play();

        tuyaux.addAll(Arrays.asList(
                new Tuyau(waterBotRight, tuyauBotRight), new Tuyau(waterTopRight, tuyauTopRight)
                , new Tuyau(waterTopMid, tuyauTopMid), new Tuyau(waterMid, tuyauMid)
                , new Tuyau(waterBotMid, tuyauBotMid), new Tuyau(waterMidRight, tuyauMidRight)
                , new Tuyau(waterTopLeft, tuyauTopLeft)
        ));
        liste_plateforme.add(plateforme0);
        liste_plateforme.add(plateforme1);
        liste_plateforme.add(plateforme2);
        liste_plateforme.add(plateforme3);
        liste_plateforme.add(plateforme4);
        liste_plateforme.add(plateforme5);
        liste_plateforme.add(plateforme6);
        waters.addAll(Arrays.asList(waterBotRight, waterTopRight, waterTopMid, waterMid, waterBotMid, waterMidRight, waterTopLeft));
        scheduler = new Scheduler((ArrayList<Tuyau>) tuyaux.clone());
        tlScheduler = new Timeline();
        tlScheduler.getKeyFrames().add(new KeyFrame(new Duration(5000), event -> scheduler.selectNextAndPlay(spriteWaterfalls)));
        tlScheduler.setCycleCount(Animation.INDEFINITE);
        tlScheduler.play();
        tlJauge = new Timeline();
        tlJauge.getKeyFrames().add(new KeyFrame(Duration.millis(100), event -> {
            int nbFalling = scheduler.getNbFalling();
            double newHeight = 0;
            double newLayout = 0;
            if (nbFalling == 0) {
                newHeight = jauge.getHeight() - 0.2;
            } else {
                newHeight = nbFalling * 0.5 + jauge.getHeight();
            }
            newLayout=MAX_Y_JAUGE-newHeight;
            if (newHeight <= MAX_Y_JAUGE-30 && newHeight > 0) {
                jauge.setHeight(newHeight);
                jauge.setLayoutY(newLayout);
            }
            System.out.println(newHeight);
            //Condition défaite
            if(newHeight >= MAX_Y_JAUGE-32)
            {
                try {
                    this.endController();
                    Main.sceneLoader.switchTo(SceneLoader.SCENE_FIN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }));
        tlJauge.setCycleCount(Animation.INDEFINITE);
        tlJauge.play();
        animation.setLayoutY(500);
        animation.setLayoutX(300);
        animation.setTranslateX(-animation.getFitWidth() / 2);
        animation.setTranslateY(-animation.getFitHeight() + 10);
        spritesCourse = new ArrayList<>();
        tlAttaque = new Timeline();
        spritesAttaque = new ArrayList<>();
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_01.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_02.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_03.png"));
        spritesAttaque.add(new Image("images/perso/repare/images/perso_repare_03.png"));
        tlEgouts = new Timeline();
        tlEgouts.getKeyFrames().add(new KeyFrame(Duration.millis(80),event -> {
            compteur++;
            Image img = spriteEgoutFalls.get(compteur%spriteEgoutFalls.size());
            waterEgouts1.setImage(img);
            waterEgouts2.setImage(img);
            waterEgouts3.setImage(img);
        }));
        tlEgouts.setCycleCount(Animation.INDEFINITE);
        tlEgouts.play();
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

        Timeline tlBase = new Timeline();
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
            animation.setLayoutX(animation.getLayoutX() + distance);
            if(animation.getLayoutX()<=-30){
                animation.setLayoutX(1225);
            }else if(animation.getLayoutX()>=1250){
                animation.setLayoutX(-25);
            }
            if (!enSaut) {
                for (ImageView plateforme : liste_plateforme) {
                    if (animation.getLayoutY() < 500) {
                        if (animation.getLayoutY() == plateforme.getLayoutY()) {
                            if (animation.getLayoutX() >= plateforme.getLayoutX() + plateforme.getFitWidth() || animation.getLayoutX() <= plateforme.getLayoutX()) {
                                imageSautIndex = 5;
                                tlSaut.play();
                            }
                        }
                    }
                }
                if (tlAttaque.getStatus() != Animation.Status.RUNNING)
                    animation.setImage(spritesCourse.get((imageCourseIndex++) % spritesCourse.size()));
            }
        }));

        tlSaut.setCycleCount(Animation.INDEFINITE);
        tlSaut.getKeyFrames().add(0, new KeyFrame(Duration.millis(60), e -> {
            if (tlAttaque.getStatus() != Animation.Status.RUNNING) {
                animation.setImage(spritesSaut.get((imageSautIndex++) % spritesSaut.size()));
            } else imageSautIndex++;

            if (imageSautIndex < 5) {
                animation.setLayoutY(animation.getLayoutY() - hauteurSaut);
            } else {
                animation.setLayoutY(animation.getLayoutY() + hauteurSaut);

                if (animation.getLayoutY() >= 500) {
                    imageSautIndex = 0;
                    animation.setLayoutY(500);
                    animation.setImage(imageBase);
                    tlSaut.stop();
                    enSaut = false;
                    mediaPlayerSaut.stop();
                } else {
                    for (ImageView plateforme : liste_plateforme) {
                        if ((Math.abs(animation.getLayoutY() - plateforme.getLayoutY()) <= 25) && animation.getLayoutX() <= plateforme.getLayoutX() + plateforme.getFitWidth() && animation.getLayoutX() >= plateforme.getLayoutX()) {
                            imageSautIndex = 0;
                            animation.setLayoutY(plateforme.getLayoutY());
                            animation.setImage(imageBase);
                            tlSaut.stop();
                            enSaut = false;
                            mediaPlayerSaut.stop();
                        }
                    }
                }
            }
        }));
    }

    public void avancerTemps()
    {
        temps+=1;
        timer.setText(Integer.toString(temps));
    }
    public void deplacement(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            distance = 22;
            animation.setScaleX(1);
            tlCourse.play();
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            distance = -22;
            animation.setScaleX(-1);
            tlCourse.play();
        } else if (keyEvent.getCode() == KeyCode.UP && tlSaut.getStatus() != Animation.Status.RUNNING) {
            String pathSaut = "src/sons/bruit saut.mp3";
            Media mediaSaut = new Media(new File(pathSaut).toURI().toString());
            mediaPlayerSaut = new MediaPlayer(mediaSaut);
            mediaPlayerSaut.play();
            enSaut = true;
            imageSautIndex = 0;
            tlSaut.play();
        } else if (keyEvent.getCode() == KeyCode.SPACE) {
            tlAttaque.play();
            ImageView imgWater = nearTuyau(animation, waters);
            if (imgWater != null) {
                Tuyau tuyau = searchByImgWater(imgWater, tuyaux);
                if (tuyau.isFalling()) {
                    String pathRepare = "src/sons/Bruit réparation.mp3";
                    Media mediaRepare = new Media(new File(pathRepare).toURI().toString());
                    mediaPlayerRepare = new MediaPlayer(mediaRepare);
                    mediaPlayerRepare.play();
                    scheduler.repare(tuyau);
                }
            }
        }
    }

    public void halte(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT) {
            animation.setImage(imageBase);
            tlCourse.stop();
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            animation.setImage(imageBase);
            tlCourse.stop();
        }
    }
    public ImageView nearTuyau(ImageView perso,ArrayList<ImageView> waters){
        for (ImageView wat:waters) {
            System.out.println("perso"+perso.getLayoutX()+" t :"+wat.getLayoutX());
            double distX = perso.getLayoutX()-(wat.getLayoutX()+wat.getFitWidth()/2);
            double distY = Math.abs(perso.getLayoutY()-(perso.getFitHeight()/2)-wat.getLayoutY());
            if(distY<=40){
                if(perso.getScaleX()>0){
                    if(distX<=20 && distX>=-65){
                        return wat;
                    }
                }else {
                    if(distX>=-20 && distX<=65){
                        return wat;
                    }
                }
            }
        }
        return null;
    }

    public Tuyau searchByImgWater(ImageView water, ArrayList<Tuyau> tuyaux) {
        for (Tuyau t : tuyaux) {
            if (t.getImgWater().equals(water)) return t;
        }
        return null;
    }

    public void stopTimeLines(){
        tlAttaque.stop();
        tlCourse.stop();
        tlSaut.stop();
        tlJauge.stop();
        tlScheduler.stop();
        tltemps.stop();
    }

    public void stopMedia(){
        if(mediaPlayerJeu.getMedia()!=null) mediaPlayerJeu.stop();
        scheduler.stopMediaEau();
    }

    public void endController(){
        stopTimeLines();
        stopMedia();
    }
}

package classes;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;


public class Tuyau {
    private ImageView imgWater;
    private ImageView imgSewel;
    private double heightInit;
    private double layoutYInit;
    private int indice;
    private Timeline timeline = new Timeline();
    public Tuyau(ImageView imgWater, ImageView imgSewel) {
        this.imgWater = imgWater;
        this.imgSewel = imgSewel;
        heightInit= imgWater.getFitHeight();
        layoutYInit = imgWater.getLayoutY();
        imgWater.setFitHeight(1);
        indice=0;
    }
    public void animateWaterFalls(ArrayList<Image> spriteWaterFalls){
        Image img;
        if(imgSewel.getCacheHint().equals(CacheHint.ROTATE)){
            img=new Image("images/tuyaux/tuyau-Vert-bad.png");
        }else {
            img=new Image("images/tuyaux/tuyau-Hor-bad.png");
        }
        imgWater.setVisible(true);
        Timeline tlAppear = new Timeline();
        tlAppear.getKeyFrames().addAll(
                new KeyFrame(new Duration(200),new KeyValue(imgWater.fitHeightProperty(),heightInit)),
                new KeyFrame(new Duration(200),new KeyValue(imgSewel.imageProperty(),img))
                );
        tlAppear.play();
        tlAppear.setOnFinished(event -> {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.getKeyFrames().add(0,new KeyFrame(Duration.millis(80),e -> imgWater.setImage(spriteWaterFalls.get((indice++)%spriteWaterFalls.size()))));
            timeline.play();
        });
    }
    public void stopWaterFalls(){
        timeline.stop();
        Image img;
        if(imgSewel.getCacheHint().equals(CacheHint.ROTATE)){
            img=new Image("images/tuyaux/tuyau-Vert-good.png");
        }else {
            img=new Image("images/tuyaux/tuyau-Hor-good.png");
        }
        Timeline tlDismiss = new Timeline();
        tlDismiss.getKeyFrames().addAll(
                new KeyFrame(new Duration(200),new KeyValue(imgSewel.imageProperty(),img)),
                new KeyFrame(new Duration(200),new KeyValue(imgWater.fitHeightProperty(),1)),
                new KeyFrame(new Duration(200),new KeyValue(imgWater.layoutYProperty(),imgWater.getFitHeight()+imgWater.getLayoutY()))
        );
        tlDismiss.play();
        tlDismiss.setOnFinished(event -> {
            imgWater.setVisible(false);
            imgWater.setLayoutY(layoutYInit);
        });
    }
    public ImageView getImgWater() {
        return imgWater;
    }
}
package classes;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;


public class WaterFall {
    private ImageView imgWater;
    private int indice;
    private static Timeline timeline = new Timeline();
    public WaterFall(ImageView imgWater) {
        this.imgWater = imgWater;
        indice=0;
    }
    public void animateWaterFalls(ArrayList<Image> spriteWaterFalls){
        imgWater.setVisible(true);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(0,new KeyFrame(Duration.millis(80),event -> {
            imgWater.setImage(spriteWaterFalls.get((indice++)%spriteWaterFalls.size()));
        }));
        timeline.play();
    }
    public void stopWaterFalls(){
        timeline.stop();
    }
    public boolean isOpen(){
        return timeline.getStatus().equals(Animation.Status.RUNNING);
    }
}

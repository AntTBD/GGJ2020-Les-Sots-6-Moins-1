package classes;

import controllers.GameController;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<WaterFall> waterFalls;
    public Scheduler(ArrayList<WaterFall> waterFalls) {
        this.waterFalls = waterFalls;
    }
    public void selectNextAndPlay(ArrayList<Image> spriteWaterFalls){
        if(!waterFalls.isEmpty()){
            int random  = (int) (Math.random() * waterFalls.size());
            WaterFall selected = waterFalls.get(random);
            waterFalls.remove(random);
            selected.animateWaterFalls(spriteWaterFalls);

        }
    }
    public void repare(){

    }
}

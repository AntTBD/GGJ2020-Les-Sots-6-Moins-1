package classes;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Tuyau> tuyaux;
    public Scheduler(ArrayList<Tuyau> tuyaux) {
        this.tuyaux = tuyaux;
    }
    public void selectNextAndPlay(ArrayList<Image> spriteWaterFalls){
        if(!tuyaux.isEmpty()) {
            int random = (int) (Math.random() * tuyaux.size());
            Tuyau selected = tuyaux.get(random);
            tuyaux.remove(random);
            selected.animateWaterFalls(spriteWaterFalls);
        }
    }
    public void repare(Tuyau tuyau){
        tuyau.stopWaterFalls();
        tuyaux.add(tuyau);
    }
}

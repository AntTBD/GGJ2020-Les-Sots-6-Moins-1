package classes;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Tuyaux> tuyauxes;
    public Scheduler(ArrayList<Tuyaux> tuyauxes) {
        this.tuyauxes = tuyauxes;
    }
    public void selectNextAndPlay(ArrayList<Image> spriteWaterFalls){
        if(!tuyauxes.isEmpty()) {
            int random = (int) (Math.random() * tuyauxes.size());
            Tuyaux selected = tuyauxes.get(random);
            tuyauxes.remove(random);
            selected.animateWaterFalls(spriteWaterFalls);
        }
    }
    public void repare(Tuyaux tuyaux){
        tuyaux.stopWaterFalls();
        tuyauxes.add(tuyaux);
    }
}

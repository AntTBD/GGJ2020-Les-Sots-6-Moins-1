package classes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Tuyau> tuyaux;
    private int ecoulement =0;
    private MediaPlayer mediaPlayerEau;

    public Scheduler(ArrayList<Tuyau> tuyaux) {
        this.tuyaux = tuyaux;
    }
    public void selectNextAndPlay(ArrayList<Image> spriteWaterFalls){
        if(!tuyaux.isEmpty()) {
            ecoulement++;
            if(ecoulement==1){
                String pathEau = "src/sons/bruit eau.mp3";
                Media mediaEau = new Media(new File(pathEau).toURI().toString());
                mediaPlayerEau = new MediaPlayer(mediaEau);
                mediaPlayerEau.play();
            }
            int random = (int) (Math.random() * tuyaux.size());
            Tuyau selected = tuyaux.get(random);
            tuyaux.remove(random);
            selected.animateWaterFalls(spriteWaterFalls);
        }
    }
    public void repare(Tuyau tuyau){
        ecoulement--;
        if(ecoulement==0){mediaPlayerEau.stop(); }
        tuyau.stopWaterFalls();
        tuyaux.add(tuyau);
    }
    public int getNbFalling(){
        return 7-tuyaux.size();
    }

    public void stopMediaEau(){
        mediaPlayerEau.stop();
    }
}

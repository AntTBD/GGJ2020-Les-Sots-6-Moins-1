package controllers;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mains.Main;
import mains.SceneLoader;

import java.io.IOException;

public class ReglesController {

    public Button BoutonFermer;

    public void RetourMenu(MouseEvent mouseEvent) throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_HOME);
    }
}

package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import mains.Main;
import mains.SceneLoader;

import java.io.IOException;

public class ReglesController {

    @FXML
    public Button BoutonFermer;

    @FXML
    public void retourMenu(MouseEvent mouseEvent) throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_HOME);
    }
}

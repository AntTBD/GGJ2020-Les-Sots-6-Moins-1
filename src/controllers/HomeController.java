package controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mains.Main;
import mains.SceneLoader;

import java.awt.*;
import java.io.IOException;

public class HomeController {
    public Text title;

    @FXML
    private void change() throws IOException {
        Main.sceneLoader.switchTo(SceneLoader.SCENE_GAME);
    }
    @FXML
    public void initialize(){
        title.setStyle("-fx-font-family: 'Amatic SC'");
    }
}

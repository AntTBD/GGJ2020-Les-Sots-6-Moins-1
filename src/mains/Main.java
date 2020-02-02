package mains;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    public static SceneLoader sceneLoader;
    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneLoader = new SceneLoader(primaryStage);
        sceneLoader.switchTo(SceneLoader.SCENE_HOME);
        primaryStage.setTitle("Sewers Madness");
        primaryStage.getIcons().add(new Image(new File("src/images/icone cle.png").toURI().toString()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

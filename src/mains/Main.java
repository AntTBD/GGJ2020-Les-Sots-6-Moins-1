package mains;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneLoader sceneLoader;
    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneLoader = new SceneLoader(primaryStage);
        sceneLoader.switchTo(SceneLoader.SCENE_HOME);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

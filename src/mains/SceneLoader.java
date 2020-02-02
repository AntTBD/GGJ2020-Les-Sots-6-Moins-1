package mains;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneLoader {
    private final Stage stage;
    public static final int SCENE_WIDTH = 1200;
    public static final int SCENE_HEIGHT=675;
    public static final int SCENE_HOME=0;
    public static final int SCENE_GAME=1;
    public static final int SCENE_FIN=2;
    public SceneLoader(Stage stage) {
        this.stage = stage;
    }
    public void switchTo(int scene) throws IOException {
        switch (scene){
            case SCENE_HOME:stage.setScene(makeSceneHome());
            break;
            case SCENE_GAME:stage.setScene(makeSceneGame());
            break;
            case SCENE_FIN:stage.setScene(makeSceneFin());
                break;
        }
    }
    private Scene makeSceneHome() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/home.fxml")));
        return new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
    }
    private Scene makeSceneGame() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/game.fxml")));
        return new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
    }

    private Scene makeSceneFin() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxmls/fin.fxml")));
        return new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
    }
}

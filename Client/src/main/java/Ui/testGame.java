package Ui;

import HappyClickGame.Lobby;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class testGame extends Application {
    GameController gameController = new GameController();
    private static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {


   //     gameController.showGame();

    }


    public static void main(String[] args) {

        launch(args);

    }

    public static void close() {
        window.close();
    }
}

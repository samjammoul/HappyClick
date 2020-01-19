package Ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class InloggenMain extends Application {
    private static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/inlog.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Sam\\Desktop\\HappyClick2019\\Client\\src\\main\\resources\\inlog.fxml").toURI().toURL());
     // Parent root = loader.load();
        window = primaryStage;
        primaryStage.setTitle("Factorial");
        primaryStage.setScene(new Scene(root, 600, 400));

        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);

    }

    public static void close() {
        window.close();
    }
}

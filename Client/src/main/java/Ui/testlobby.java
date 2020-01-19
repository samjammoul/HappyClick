package Ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class testlobby extends Application {
    private static Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/lobby.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Sam\\Desktop\\HappyClick2019\\Client\\src\\main\\resources\\inlog.fxml").toURI().toURL());
        // Parent root = loader.load();
        primaryStage.setTitle("Factorial");
        primaryStage.setScene(new Scene(root, 600, 400));
        window = primaryStage;
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);

    }

    public static void close() {
        window.close();
    }
}

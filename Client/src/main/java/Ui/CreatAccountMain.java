package Ui;


import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatAccountMain {

    private static Stage primaryStage;


    public static void showCreatAccountUi() {
        try {
            Platform.runLater(()->{
                primaryStage = new Stage();
                HBox hbox = new HBox();
                Parent root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LobbyMain.class.getResource("/creatAccount.fxml"));

                    hbox.getChildren().add(fxmlLoader.load());

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Sam\\Desktop\\HappyClick2019\\Client\\src\\main\\resources\\inlog.fxml").toURI().toURL());
                // Parent root = loader.load();
                primaryStage.setTitle("Creat account");
                Scene s = new Scene( hbox,600, 400);
                primaryStage.setScene(s);

                primaryStage.show();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void closeUi(){
        primaryStage.close();
    }


}

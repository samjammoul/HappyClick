package Ui;

import HappyClickGame.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public  class LobbyMain  {
    private static Stage primaryStage;
    private static final   ListView<User> listView = new ListView();

    public static void showLobbyUi() {
        try {
            Platform.runLater(()->{
                 primaryStage = new Stage();
                HBox hbox = new HBox();
                Parent root = null;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LobbyMain.class.getResource("/lobby.fxml"));

                    listView.setPrefSize(200, 250);
                    listView.setLayoutX(144);
                    listView.setLayoutY(137);
                    listView.setEditable(true);
                    hbox.getChildren().add(fxmlLoader.load());
                    hbox.getChildren().add(listView);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Sam\\Desktop\\HappyClick2019\\Client\\src\\main\\resources\\inlog.fxml").toURI().toURL());
                // Parent root = loader.load();
                primaryStage.setTitle("Factorial");
                Scene s = new Scene(hbox, 600, 400);
                primaryStage.setScene(s);

                primaryStage.show();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addiTemsToList(ObservableList<User> onlineUsersViewlist){
        Platform.runLater(()->{
            listView.getItems().clear();
        listView.getItems().addAll(onlineUsersViewlist);
        });

    }

    public static int getSelectedUser(){
       int userId= listView.getSelectionModel().getSelectedItem().getUserNumber();
         return userId;
    }

    public static void closeUi(){
        primaryStage.close();
    }


}
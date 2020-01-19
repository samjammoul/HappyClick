package Ui;

import HappyClickGame.Lobby;
import HappyClickGame.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


import java.util.List;

public  class  mian extends Application {


  static   ObservableList<User> onlineUsersViewlist = FXCollections.observableArrayList();
  Lobby lobby = new Lobby();
    ListView listView = new ListView();
    public static void addOnlineUsers(List<User> users){
        onlineUsersViewlist.addAll(users);

    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ListView Experiment 1");





        HBox hbox = new HBox(listView);
        onlineUsersViewlist.addAll(lobby.getOnlineUsers());
        listView.getItems().add(onlineUsersViewlist);
        listView.getItems().addAll(onlineUsersViewlist);
        Scene scene = new Scene(hbox, 300, 120);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

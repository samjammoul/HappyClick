package Ui;

import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import HappyClickGame.ILobbyControllerFactory;
import HappyClickGame.IRequestUiController;
import HappyClickGame.IRequestUiControllerFactory;
import HappyClickGame.LobbyFactory;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class RequestUiMain {



    private static Button rejectBtn = new Button();
    private static Button acceptBtn = new Button();
    private static Label requestText = new Label();
    private static Label senderNameLebal = new Label();
    private static Stage primaryStage;
    /*
    private IRequestUiControllerFactory iRequestUiControllerFactory = new RequestUiControllerFactory ();
    private IRequestUiController iRequestUiController = iRequestUiControllerFactory.getRequestUiControllerForUi();

     */


/*
    public void showLobbyUi(String senderName) {
        try {
            Platform.runLater(()->{
                primaryStage = new Stage();
                HBox hbox = new HBox();
                Parent root = null;
                try {


                    requestText.setPrefSize(149, 17);
                    requestText.setLayoutX(62);
                    requestText.setLayoutY(46);
                    requestText.setText("You get request from :");

                    rejectBtn.setPrefSize(149, 25);
                    rejectBtn.setLayoutX(54);
                    rejectBtn.setLayoutY(92);
                    rejectBtn.setText("Reject");

                    acceptBtn.setPrefSize(149, 25);
                    acceptBtn.setLayoutX(231);
                    acceptBtn.setLayoutY(92);
                    acceptBtn.setText("Accept");

                    senderNameLebal.setPrefSize(110, 17);
                    senderNameLebal.setLayoutX(227);
                    senderNameLebal.setLayoutY(46);
                    senderNameLebal.setText(senderName);


                    hbox.getChildren().add(rejectBtn);
                    hbox.getChildren().add(acceptBtn);
                    hbox.getChildren().add(requestText);
                    hbox.getChildren().add(senderNameLebal);
                    acceptBtn.setOnAction(e -> acceptRequest());
                    rejectBtn.setOnAction(e -> rejectRequest());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                primaryStage.setTitle("Request");
                Scene s = new Scene(hbox, 436, 138);
                primaryStage.setScene(s);

                primaryStage.show();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void closeWindow(){
        Platform.runLater(()->{
        primaryStage.close();  });
    }
     */

/*
    public void acceptRequest(){
        iRequestUiController.acceptRequest();
    }

    public void rejectRequest(){
        iRequestUiController.rejectRequest();
    }

 */




}

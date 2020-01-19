package Ui;

import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import HappyClickGame.IRequestUiController;
import HappyClickGame.Lobby;
import HappyClickGame.LobbyFactory;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import share.RequestData;

public class RequestUiController implements IRequestUiController {

 //   private static RequestUiMain requestUiMain;
    private static  RequestData requestData ;
   // private ILobbyFactory lobbyFactory= new LobbyFactory();
    private Ilobby ilobby ;
           //= lobbyFactory.getLobby();

    ///////////////////////
    private static Button rejectBtn = new Button();
    private static Button acceptBtn = new Button();
    private static Label requestText = new Label();
    private static Label senderNameLebal = new Label();
    private static Stage primaryStage;
    /////////////////////////////////////////////////


    public  RequestUiController( Lobby lobby){
       // requestUiMain = new RequestUiMain();

       ilobby = lobby;
    }

public void showLobbyUi(String senderName) {
    try {
        Platform.runLater(()->{
            primaryStage = new Stage();
            AnchorPane anchorPane = new AnchorPane();
            Parent root = null;
            try {


                anchorPane.getChildren().add(rejectBtn);
                anchorPane.getChildren().add(acceptBtn);
                anchorPane.getChildren().add(requestText);
                anchorPane.getChildren().add(senderNameLebal);




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




                acceptBtn.setOnAction(e -> acceptRequest());
                rejectBtn.setOnAction(e -> rejectRequest());
            } catch (Exception e) {
                e.printStackTrace();
            }

            primaryStage.setTitle("Request");
            Scene s = new Scene(anchorPane, 436, 138);
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

    public void getRequest(RequestData requestData){
        this.requestData = requestData;
       // requestUiMain.showLobbyUi(requestData.getUserSender().getName());
        showLobbyUi(requestData.getUserSender().getName());
    }
    public  RequestData getRequestData(){
        return requestData;
    }

    public void acceptRequest(){
        ilobby.acceptRequest(requestData);
        closeUi();
    }

    public void rejectRequest(){
        ilobby.rejectRequest(requestData);
        closeUi();
    }

    public int getRequestId(){
        return requestData.getRequestId();
    }
    public int getGameIdOfRequest(){
        return requestData.getGameId();
    }

    public void closeUi(){
       // requestUiMain.closeWindow();
        Platform.runLater(()->{
            primaryStage.close();  });
    }

}

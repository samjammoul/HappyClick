package Ui;

import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import HappyClickGame.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import share.PlayerData;
import share.RequestData;
import share.RequestStatus;

import java.util.ArrayList;
import java.util.List;

public class LobbyController implements IlobbyController {

    @FXML
    private AnchorPane chatsMenuBox;

    @FXML
    private ScrollPane scrol;

    @FXML
    private Button chatsMenuCloseBtn;


    @FXML
    private Button newGameBtn;

    @FXML
    private Button joinGameBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private ListView<User> onlinelistlistview ;


    @FXML
    private ListView<String> onlinelistlistview1;

    @FXML
    private Button sendRequestBtn;




   // Transformer transformer = new Transformer();
    private List<User> onlineusers = new ArrayList<>();
    private  ObservableList<User> OnlineUsersViewlist = FXCollections.observableArrayList();
    private static boolean ableToSendInvite;
    private ILobbyFactory lobbyFactory = new LobbyFactory();
    private Ilobby ilobby = lobbyFactory.getLobby();
   // private GameHandler gameHandler;




    public void initialize(){
        ableToSendInvite = false;
     //   System.out.println("test2q3");
        //sendRequestBtn.setDisable(true);

    }


    @FXML
    void chatsMenusClose(ActionEvent event) {

    }

    @FXML
    void joinGame(ActionEvent event) {

    }

    @FXML
    void logout(ActionEvent event) {
        /*
        LobbyMain.showLobbyUi();
        OnlineUsersViewlist.add(new User(2,"hey"));
        LobbyMain.addiTemsToList(OnlineUsersViewlist);

         */
    }

    @FXML
    void showFriendsRequests(ActionEvent event) {

    }

    @FXML
    void startNewGame(ActionEvent event) {
        ilobby.creatNewGame();
    }

    public  void getOnlineUsers(List<User> users) {

        System.out.println(users.size());

        OnlineUsersViewlist.clear();

        OnlineUsersViewlist.addAll(users);

    LobbyMain.addiTemsToList(OnlineUsersViewlist);

    }


   public void ableSendRequest(){
       Platform.runLater(()->{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Info");
           alert.setHeaderText(" ");
           alert.setContentText("Now you can send and get requests");
           alert.showAndWait();

       });

     ableToSendInvite = true;
    }

    @FXML
    void sendRequest(ActionEvent event) {
        if(ableToSendInvite){
       try {
                int receiverId =  LobbyMain.getSelectedUser();
                if(receiverId> 0){
                    ilobby.sendInviteToUser(receiverId);
                }else {
                    Platform.runLater(()->{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(" ");
                        alert.setContentText("There is an error");
                        alert.showAndWait();
                       // sendRequestBtn.setVisible(true);
                    });
                }

         }
        catch (Exception ex){
         System.out.println(ex);
        }

        }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(" ");
                alert.setContentText("Please start a game first");
                alert.showAndWait();
                sendRequestBtn.setVisible(true);
        }

    }

    @Override
    public void requestIsSent(String receiverName){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request information");
            alert.setHeaderText("Request is sent! ");
            alert.setContentText("Your you request to "+ receiverName +" is sent." );
            alert.showAndWait();

        });

    }

    public void showToUser(String message){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request information");
            alert.setHeaderText(message);
            alert.setContentText(" " );
            alert.showAndWait();

        });
    }

    public void closeUi(){
        Platform.runLater(()->{
            LobbyMain.closeUi();
        });

    }
    public void showUi(){
        Platform.runLater(()->{
            LobbyMain.showLobbyUi();
        });
    }

    public void showResult(List<PlayerData> playerData){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request information");
            for (PlayerData player:playerData) {
                alert.setContentText("Name of player "+ player.getName() + " || Score: " + player.getScore()     );
            }
            alert.setHeaderText("Result of Game");

            alert.showAndWait();

        });
    }


   }

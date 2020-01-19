package Ui;

import HappyClickGame.ICommunicator;
import HappyClickGame.AccountHandler;
import HappyClickGame.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class InloggenController implements IInloggenController {

    @FXML
    private AnchorPane labelPassword;

    @FXML
    private TextField userNameTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private Button logIn;

    @FXML
    private Label tst;

    @FXML
    private Label labelEmail;

    @FXML
    private Label tst11;

    @FXML
    private Button creatAccount;

    @FXML
    private ListView<String> onlinelistlistview1;


    /////// ---   Instances  ---  /////////


    ICommunicator  communicator;
    AccountHandler accountHandler = new AccountHandler();
    Stage stage;

    ObservableList<User> onlineUsersViewlist = FXCollections.observableArrayList();
    public void addOnlineUsers(List<User> users){
        onlineUsersViewlist.addAll(users);
    }
    @FXML
    void open(ActionEvent event) {

        Parent root;
        try {
            FXMLLoader fxmlLoader   = new FXMLLoader(getClass().getResource("/view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.show();
            InloggenMain.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Login(ActionEvent event) {
        if( passwordTextBox.getText().equals(" ") || userNameTextBox.getText().equals(" ")  ||
                passwordTextBox.getText() == null || userNameTextBox.getText() == null   ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro !");
            alert.setHeaderText(" ");
            alert.setContentText(" Please enter value in all fields !");
            alert.showAndWait();

        }else {
            accountHandler.login(userNameTextBox.getText(),passwordTextBox.getText());
        }

    }

    @FXML
    void openCreatAccountPage(ActionEvent event) {

        Platform.runLater(()->{
            CreatAccountMain.showCreatAccountUi();
            InloggenMain.close();
        });
    }


    @Override
    public void loginSucceed() {
        LobbyMain.showLobbyUi();
        Platform.runLater(()->{
        InloggenMain.close();
        });
    }

    @Override
    public void loginfailed() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro !");
            alert.setHeaderText(" ");
            alert.setContentText(" Please try another user name or try later 1");

            alert.showAndWait();
        });
    }


}

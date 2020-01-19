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

        try {

       //     onlinelistlistview1 = new ListView<String>();
            ObservableList<String> items = FXCollections.observableArrayList (
                    "Single", "Double", "Suite", "Family App");
            onlinelistlistview1.setItems(items);

            /*
            FXMLLoader fxmlLoader   = new FXMLLoader(getClass().getResource("/creatAccount.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1, 600, 400));
            stage.show();
            InloggenMain.close();

             */


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void loginSucceed() {
        /*
        try {
            Platform.runLater(()->{
                Stage primaryStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/lobby.fxml"));
                    final ListView listView = new ListView(onlineUsersViewlist);
                    listView.setPrefSize(200, 250);
                    listView.setLayoutX(144);
                    listView.setLayoutY(137);
                    listView.setEditable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //FXMLLoader loader = new FXMLLoader(new File("C:\\Users\\Sam\\Desktop\\HappyClick2019\\Client\\src\\main\\resources\\inlog.fxml").toURI().toURL());
                // Parent root = loader.load();
                primaryStage.setTitle("Factorial");
                Scene s = new Scene(root, 600, 400);
                primaryStage.setScene(s);

                primaryStage.show();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }

         */
        LobbyMain.showLobbyUi();
        Platform.runLater(()->{
        InloggenMain.close();
        });
    }

    @Override
    public void loginfailed() {
        Platform.runLater(()->{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro !");
        alert.setHeaderText(" ");
        alert.setContentText(" Please try another user name or try later 1");

        alert.showAndWait();
        });
    }
}

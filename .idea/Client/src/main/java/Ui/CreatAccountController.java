package Ui;

import CommunicatorClient.CommunicatorClientWebSocket;
import HappyClickGame.ICommunicator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatAccountController implements ICreatAccountController {


    @FXML
    private TextField userNameTextBox;

    @FXML
    private TextField passwordTextBox;

    @FXML
    private Label creatAccountLabel;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelPassword;

    @FXML
    private Button creatAccount;

    @FXML
    private TextField rePasswordTextBox;

    @FXML
    private Label labelRePassword;




    /////// ---   Instances  ---  /////////


    ICommunicator communicator;




    @FXML
    void creatAccount(ActionEvent event) {

        if(rePasswordTextBox.getText().equals(" ") || passwordTextBox.getText().equals(" ") || userNameTextBox.getText().equals(" ") || rePasswordTextBox.getText() == null ||
                passwordTextBox.getText() == null || userNameTextBox.getText() == null  ){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro !");
            alert.setHeaderText(" ");
            alert.setContentText(" Please enter value in all fields !");

            alert.showAndWait();

        }
         System.out.println(rePasswordTextBox.getText());
        System.out.println( passwordTextBox.getText());
        if(rePasswordTextBox.getText().equals(passwordTextBox.getText()) )
        {
            registerSucceed();
            communicator = CommunicatorClientWebSocket.getInstance();

            // Establish connection with server
            communicator.start();
          communicator.register(userNameTextBox.getText(),passwordTextBox.getText());

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro !");
            alert.setHeaderText(" ");
            alert.setContentText(" Please enter same password !");

            alert.showAndWait();
        }

    }

    @Override
    public  void registerSucceed() {
        try {
            Platform.runLater(()->{
                Stage primaryStage = new Stage();
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
                primaryStage.show();
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registerfailed() {
        Platform.runLater(()->{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro !");
        alert.setHeaderText(" ");
        alert.setContentText(" Please try another user name or try later ");

        alert.showAndWait();
        });
    }


}

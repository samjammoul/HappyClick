package Ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InloggenController {

    @FXML
    private AnchorPane UiScreen;

    @FXML
    private Button newUI;

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

    }
}

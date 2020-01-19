package Ui;



import CommunicatorClient.IGame;
import CommunicatorClient.IGameFactory;
import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import HappyClickGame.IGameController;
import HappyClickGame.LobbyFactory;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import share.AnswerData;
import share.QuestionData;

import java.util.List;

public class GameController implements IGameController {

    private static Button answer1 = new Button();
    private static Button answer2 = new Button();
    private static Button answer3 = new Button();
    private static Button answer4 = new Button();
    private static Label questionLabel = new Label();
    private static Label questionText = new Label();
    private static ImageView imageView = new ImageView();
    private static Stage primaryStage;
    private  QuestionData questionData;
    private ILobbyFactory lobbyFactory = new LobbyFactory();
    private Ilobby ilobby = lobbyFactory.getLobby();
  //  private IGameFactory iGameFactory = new GameFactory();
    private IGame iGame ;



   public void showGame(QuestionData question) {
       try {
           this.questionData = question;
           Platform.runLater(() -> {
               primaryStage = new Stage();
               AnchorPane anchorPane = new AnchorPane();
               Parent root = null;
               try {


                   anchorPane.getChildren().add(answer1);
                   anchorPane.getChildren().add(answer2);
                   anchorPane.getChildren().add(answer3);
                   anchorPane.getChildren().add(answer4);
                   anchorPane.getChildren().add(questionLabel);
                   anchorPane.getChildren().add(questionText);
                   anchorPane.getChildren().add(imageView);


                   questionLabel.setPrefSize(59, 46);
                   questionLabel.setLayoutX(115);
                   questionLabel.setLayoutY(39);
                  questionLabel.setText("Question:");


                   questionText.setPrefSize(751, 46);
                   questionText.setLayoutX(185);
                   questionText.setLayoutY(39);
                  questionText.setText(questionData.getTextQuestion());


                   imageView.setFitWidth(609);
                   imageView.setFitHeight(305);
                   imageView.setLayoutX(115);
                   imageView.setLayoutY(98);
                   imageView.setImage(new Image(questionData.getPicName()+".jpg"));

                   answer1.setPrefSize(333, 53);
                   answer1.setLayoutX(48);
                   answer1.setLayoutY(447);
                   answer1.setText(questionData.getAnswerList().get(0).getAnswerText());

                   answer2.setPrefSize(333, 53);
                   answer2.setLayoutX(48);
                   answer2.setLayoutY(547);
                   answer2.setText(questionData.getAnswerList().get(1).getAnswerText());

                   answer3.setPrefSize(333, 53);
                   answer3.setLayoutX(513);
                   answer3.setLayoutY(447);
                   answer3.setText(questionData.getAnswerList().get(2).getAnswerText());

                   answer4.setPrefSize(333, 53);
                   answer4.setLayoutX(513);
                   answer4.setLayoutY(535);
                   answer4.setText(questionData.getAnswerList().get(3).getAnswerText());

                   answer1.setOnAction(e -> selectAnswer(questionData.getAnswerList().get(0)));
                   answer2.setOnAction(e -> selectAnswer(questionData.getAnswerList().get(1)));
                   answer3.setOnAction(e -> selectAnswer(questionData.getAnswerList().get(2)));
                   answer4.setOnAction(e -> selectAnswer(questionData.getAnswerList().get(3)));







               } catch (Exception e) {
                   e.printStackTrace();
               }

               primaryStage.setTitle("Game");
               Scene s = new Scene(anchorPane, 1015, 656);
               primaryStage.setScene(s);

               primaryStage.show();
           });
       }catch (Exception ex){
           System.out.println(ex);
       }
   }

   public void selectAnswer(AnswerData answer){
       iGame = ilobby.getGame();
       answer.setGameId(questionData.getGameId());
       answer.setQuestionId(questionData.getQuestionId());
       System.out.println(answer.getQuestionId());
       iGame.selectAnswer(answer);
       answer1.setDisable(true);
       answer2.setDisable(true);
       answer3.setDisable(true);
       answer4.setDisable(true);

   }

   public void updateQuestion(QuestionData question){
       this.questionData = question;
       Platform.runLater(()->{
       questionText.setText(questionData.getTextQuestion());
       answer1.setText(questionData.getAnswerList().get(0).getAnswerText());
       answer2.setText(questionData.getAnswerList().get(1).getAnswerText());
       answer3.setText(questionData.getAnswerList().get(2).getAnswerText());
       answer4.setText(questionData.getAnswerList().get(3).getAnswerText());
       imageView.setImage(new Image(questionData.getPicName()+".jpg"));
           answer1.setDisable(false);
           answer2.setDisable(false);
           answer3.setDisable(false);
           answer4.setDisable(false);
       });

   }

  public void closeUi(){
      Platform.runLater(()->{
       primaryStage.close();
      });
  }

}

package HappyClickGame;

import CommunicatorClient.CommunicatorClientWebSocket;
import CommunicatorClient.IGame;
import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import Ui.GameControllerFactory;
import Ui.LobbyControllerFactory;
import share.AnswerData;
import share.PlayerData;
import share.QuestionData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Game implements IGame {

   private int gameId;
   private IGameControllerFactory iGameControllerFactory =  new GameControllerFactory();
   private IGameController iGameController = iGameControllerFactory.getGameController();
   private ICommunicator iCommunicator;
   private LobbyFactory lobbyFactory = new LobbyFactory();
   private Lobby lobby = lobbyFactory.getLobby();

public Game(int gameId){
   this.gameId = gameId;

}
   public Game( ){

   }

   public int getGameId() {
      return gameId;
   }

   public void setGameId(int gameId){
     this.gameId = gameId;
   }

   public void getQuestion(QuestionData questionData){
      iGameController.updateQuestion(questionData);
   }

   public void selectAnswer(AnswerData answer){
      answer.setPlayerId(lobby.getUser().getUserNumber());
      iCommunicator = CommunicatorClientWebSocket.getInstance();
      iCommunicator.sendAnswer(answer);
   }

   public void startGame(QuestionData questionData){
      iGameController.showGame(questionData);

   }

   public void closeUi(){
      iGameController.closeUi();
   }

}

package Server;

import javassist.compiler.ast.Pair;
import share.*;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GameHandler {
    private static ArrayList<Game> games = new ArrayList<>();
    private UserHandler userHandler;

    public GameHandler(){
        userHandler = new UserHandler();
    }

    public GameData creatGame(PlayerData player,Session session){
        Game game =new Game();
        game.setUpGame(new Player(player.getPlayerNumber(),player.getName(),session));
         games.add(game);
         game.setGameId(games.size()+1);
         return new GameData(game.getGameId());

    }

    public  Map<RequestData ,Session>sendRequest(RequestData requestData) {
        Game game;
        Map<RequestData, Session> requestMap = new HashMap<>();
         User receiver = userHandler.checkIfOnlineUser(new User(requestData.getUserReceiver().getUserId()));
         if(receiver.getUserNumber()> 0){
             requestData.setUserReceiver(new UserData(receiver.getUserNumber(),receiver.getName()));
             requestData.setRequestStatus(RequestStatus.Waiting);
             game = getGame(requestData.getGameId());
             game.addRequest(requestData);
             requestMap.put(requestData,receiver.getSession());
             return requestMap;
         }else {
             return requestMap;
         }
    }

    public void requestIsSent(RequestData requestData,Session  session){
        Game game = getGame(requestData.getGameId());
        game.setTimerForRequest(requestData,session);
    }

    public Game getGame(int gameId){

      for (Game g:games) {
          if(g.getGameId() == gameId){

              return g;

          }
      }
      return new Game(-1);
    }

    public void requestResponse(RequestData requestData){
        Game game = getGame(requestData.getGameId());
        game.changeRequestStatus(requestData.getRequestId(),requestData.getRequestStatus());
    }

    public synchronized void getPlayerAnswer(AnswerData answerData){
       Game game = getGame(answerData.getGameId());
        int indexOfGame = games.indexOf(game);
        games.get(indexOfGame).addEnteredAnswer(new EnteredAnswer(answerData.getQuestionId(),answerData.getAnswerId(),answerData.getPlayerId()));
    }

    public void deleteGame(int gameId)
    {
        Game game = getGame(gameId);
        games.remove(game);
    }

}

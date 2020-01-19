package Server;

import org.junit.jupiter.api.Test;
import share.*;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
   private   Session session = null;
   private   Game game;
   private GameHandler gameHandler = new GameHandler();
   private Set set = new Set();



     // if the three questions are correct
     @Test
    void checkAnswerSetScore_SetScoreForCorrectAnswer_CorrectScoreIsSet() throws IOException {

        int expectScorePlayer1 = 30;


        GameData gameData =  gameHandler.creatGame(new PlayerData(1, "P1"),session);
        game = gameHandler.getGame(gameData.getGameId());
        game.addPlayer(new Player(2,"P2"));

        set.setUpQuestions(game);

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,3,4));



        game.checkAnswerSetScore();
         game.setPlayersScore();
        List<Player> players = game.getPlayers();

      int ScorePlayer1 =  players.get(0).getScore();

        assertEquals(expectScorePlayer1,ScorePlayer1);

    }



    // if the three questions are not correct
    @Test
    void checkAnswerSetScore2_SetScoreForCorrectAnswer_CorrectScoreIsSet() throws IOException {

        int expectScorePlayer1 = 0;


        GameData gameData =  gameHandler.creatGame(new PlayerData(1, "P1"),session);
        game = gameHandler.getGame(gameData.getGameId());
        game.addPlayer(new Player(2,"P2"));

        set.setUpQuestions(game);

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,1,0));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,2,0));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,3,0));



        game.checkAnswerSetScore();
        game.setPlayersScore();
        List<Player> players = game.getPlayers();

        int ScorePlayer1 =  players.get(0).getScore();

        assertEquals(expectScorePlayer1,ScorePlayer1);

    }



    // if 2 players has 3 correct answer

    @Test
    void checkAnswerSetScore3_SetScoreForCorrectAnswer_CorrectScoreIsSet() throws IOException {

        int expectScorePlayer1 = 30;
        int expectScorePlayer2 = 24;

        GameData gameData =  gameHandler.creatGame(new PlayerData(1, "P1"),session);
        game = gameHandler.getGame(gameData.getGameId());
        game.addPlayer(new Player(2,"P2"));

        set.setUpQuestions(game);

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,3,4));

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,3,4));


        game.checkAnswerSetScore();
        game.setPlayersScore();
        List<Player> players = game.getPlayers();

        int ScorePlayer1 =  players.get(0).getScore();
        int ScorePlayer2 =  players.get(1).getScore();

        assertEquals(expectScorePlayer1,ScorePlayer1);
        assertEquals(expectScorePlayer2,ScorePlayer2);

    }


    // if 4 players has 3 correct answer

    @Test
    void checkAnswerSetScore4_SetScoreForCorrectAnswer_CorrectScoreIsSet() throws IOException {

        int expectScorePlayer1 = 30;
        int expectScorePlayer2 = 24;
        int expectScorePlayer3 = 18;
        int expectScorePlayer4 = 12;

        GameData gameData =  gameHandler.creatGame(new PlayerData(1, "P1"),session);
        game = gameHandler.getGame(gameData.getGameId());
        game.addPlayer(new Player(2,"P2"));
        game.addPlayer(new Player(3,"P2"));
        game.addPlayer(new Player(4,"P2"));

        set.setUpQuestions(game);

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,3,4));

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,3,4));

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,3,4));

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,1,2));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,2,1));
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,3,4));


        game.checkAnswerSetScore();
        game.setPlayersScore();
        List<Player> players = game.getPlayers();

        int ScorePlayer1 =  players.get(0).getScore();
        int ScorePlayer2 =  players.get(1).getScore();
        int ScorePlayer3 =  players.get(2).getScore();
        int ScorePlayer4 =  players.get(3).getScore();

          assertEquals(expectScorePlayer1,ScorePlayer1);
          assertEquals(expectScorePlayer2,ScorePlayer2);
          assertEquals(expectScorePlayer3,ScorePlayer3);
          assertEquals(expectScorePlayer4,ScorePlayer4);

    }


    // variety score

    @Test
    void checkAnswerSetScore5_SetScoreForCorrectAnswer_CorrectScoreIsSet() throws IOException {

        int expectScorePlayer1 = 22; //22
        int expectScorePlayer2 = 16;
        int expectScorePlayer3 = 16;
        int expectScorePlayer4 = 10;

        GameData gameData =  gameHandler.creatGame(new PlayerData(1, "P1"),session);
        game = gameHandler.getGame(gameData.getGameId());
        game.addPlayer(new Player(2,"P2"));
        game.addPlayer(new Player(3,"P3"));
        game.addPlayer(new Player(4,"P4"));

        set.setUpQuestions(game);


        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,1,2)); // 10
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,1,2)); //8
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,1,2)); // 6
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,1,2)); //4

        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,2,2));  // 0
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,2,1));  //10
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,2,1));  // 8
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,2,2));  // 0



        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),3,3,2));  //0
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),2,3,4)); // 10
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),1,3,4)); // 8
        gameHandler.getPlayerAnswer(new AnswerData(gameData.getGameId(),4,3,2)); // 0





        game.checkAnswerSetScore();
        game.setPlayersScore();
        List<Player> players = game.getPlayers();

        int ScorePlayer1 =  players.get(0).getScore();
        int ScorePlayer2 =  players.get(1).getScore();
        int ScorePlayer3 =  players.get(2).getScore();
        int ScorePlayer4 =  players.get(3).getScore();

        assertEquals(expectScorePlayer1,ScorePlayer1);
        assertEquals(expectScorePlayer2,ScorePlayer2);
        assertEquals(expectScorePlayer3,ScorePlayer3);
        assertEquals(expectScorePlayer4,ScorePlayer4);

    }


}

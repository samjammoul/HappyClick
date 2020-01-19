package Server;

import java.util.TimerTask;

public class GameHelper extends TimerTask {

    private static int time = 0;
    private static int sentQuestion = 0;
    private Game game;

    public GameHelper(Game game){
        this.game = game;
    }



    @Override
    public void run() {
        time++;
        //     System.out.println("Timer ran " + ++v);
        if( sentQuestion != 0 && sentQuestion < game.getQuestionsListSize()){
            game.sendQuestionToPlayer(sentQuestion); // method to send question
            sentQuestion++;
        }
        if(sentQuestion ==0 ){
            game.sendToPlayersToStartTheGame(0 );
            sentQuestion++;
        }


        if(time == game.getQuestionsListSize()+1){
           game.gameIsFinish();
        }
    }
}

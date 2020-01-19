package Server;

import share.AnswerData;

public class QuestionHelper extends Thread {

    private AnswerData answerData;
    GameHandler gameHandler = new GameHandler();

    public QuestionHelper(AnswerData answerData){
        this.answerData = answerData;
    }

    @Override
    public void run() {
      gameHandler.getPlayerAnswer(answerData);
    }
}

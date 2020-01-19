package Server;

public class EnteredAnswer {
    private int playerNumber;
    private int answerId;
    private int questionId;
    private int answerScore;

    public EnteredAnswer(int questionId,int answerId,int playerNumber){
         this.questionId =questionId;
         this.answerId =answerId;
           this.playerNumber=playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getAnswerScore() {
        return answerScore;
    }

    public void setAnswerScore(int answerScore) {
        this.answerScore = answerScore;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}

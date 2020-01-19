package share;

public class AnswerData {
    private String answerText;
    private  int gameId;
    private  int playerId;
    private  int questionId;
    private   int answerId;

    public AnswerData(int gameId,int playerId,int questionId,int answerId){
        this.gameId = gameId;
        this.playerId = playerId;
        this.questionId = questionId;
        this.answerId=answerId;

    }

    public AnswerData(int gameId,String answerText,int answerId){
        this.gameId = gameId;
        this.answerText = answerText;
        this.answerId = answerId;
    }


    public  int getGameId() {
        return gameId;
    }

    public  void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public  int getPlayerId() {
        return playerId;
    }

    public  void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public  int getQuestionId() {
        return questionId;
    }

    public  void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public  int getAnswerId() {
        return answerId;
    }

    public  void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}

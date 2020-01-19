package Server;

public class Answer {
    private int answerId;
    private String answerText;

    public Answer(int answerId,String answerText){
        this.answerId=answerId;
        this.answerText=answerText;
    }

    public Answer(String answerText){
        this.answerText=answerText;
    }


    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}

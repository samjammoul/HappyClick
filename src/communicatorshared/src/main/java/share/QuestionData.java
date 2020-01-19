package share;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {
    private int  questionId;
    private int  gameId;
    private String textQuestion;
    private String picName;
    private  List<AnswerData> answers;


    public QuestionData(int questionId,String textQuestion, String picName,List<AnswerData> answers,int gameId){
        this.questionId=questionId;
        this.textQuestion = textQuestion;
        this.picName = picName;
        this.gameId = gameId;
        this.answers = answers;


    }

    public  List<AnswerData> getAnswers() {
        return answers;
    }

    public  void setAnswers(List<AnswerData> answers) {
        answers = answers;
    }


    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public List<AnswerData> getAnswerList(){
        return answers;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}

package Server;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private int  questionId;
    private int  gameId;
    private String textQuestion;
    private String picName;
    private  List<Answer> answers;
    private  List<EnteredAnswer> enteredAnswers ;
    private  Answer correctAnswer;

    public Question(int questionId,String textQuestion, String picName,List<Answer> answers,Answer correctAnswer ){
        this.questionId=questionId;
        this.textQuestion = textQuestion;
        this.picName = picName;
        this.answers = answers;
        this.correctAnswer=correctAnswer;
        this.enteredAnswers = new ArrayList<>();
    }

    public Question(int questionId){
        this.questionId = questionId;
    }

    public  Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public  void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


    public void addAnswer(Answer answer){
        answers.add(answer);
        answer.setAnswerId(answers.size()+1);
    }
    public void addEnteredAnswer(EnteredAnswer answer){
        enteredAnswers.add(answer);

    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public List<EnteredAnswer> getEnteredAnswers(){
        return enteredAnswers;
    }

    public List<Answer> getAnswers(){
        return answers;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}

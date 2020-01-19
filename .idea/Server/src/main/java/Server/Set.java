package Server;

import java.util.ArrayList;
import java.util.List;

public class Set {

    // houn almashkleah
    public void setUpQuestions(Game game){
        //Q1
    addQuestion(game,"what's the name of this movie?","1", "Joker","IT","Spider-man","Titans",new Answer("IT"));
        //Q2
      addQuestion(game,"what's the name of this movie?","2", "Joker","IT","Batman","Avengers",new Answer("Joker"));
        //Q3
    addQuestion(game,"what's the name of this series?","3", "Black Mirror","IT","Spider-man","stranger things",new Answer("stranger things"));
    }

    public void addQuestion(Game game,String questionText,String picName,String  answer1,String  answer2,String  answer3,String  answer4,Answer correctAnswer){

        List <Answer> answerList = new ArrayList<>();

        answerList.add( new Answer(answerList.size()+1 ,answer1));
        answerList.add(new Answer(answerList.size()+1 ,answer2));
        answerList.add(new Answer(answerList.size()+1 ,answer3));
        answerList.add(new Answer(answerList.size()+1 ,answer4));
        for (Answer  answer : answerList){
            if(answer.getAnswerText() == correctAnswer.getAnswerText()){
               correctAnswer.setAnswerId(answer.getAnswerId());
               break;
            }
        }
        Question question = new Question(game.getQuestionsListSize()+1,questionText,picName,answerList,correctAnswer);
        game.addQuestion(question);

    }
}

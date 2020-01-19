package CommunicatorClient;

import share.AnswerData;
import share.QuestionData;

public interface IGame {

    void getQuestion(QuestionData questionData);
    void selectAnswer(AnswerData answer);
    void startGame(QuestionData questionData);
}

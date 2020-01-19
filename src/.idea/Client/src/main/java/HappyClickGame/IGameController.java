package HappyClickGame;

import share.QuestionData;

public interface IGameController {

    void updateQuestion(QuestionData questionData);
    void showGame(QuestionData question);
    void closeUi();
}

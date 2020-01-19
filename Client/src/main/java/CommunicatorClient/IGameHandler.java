package CommunicatorClient;

import HappyClickGame.Game;
import share.CommunicatorWebSocketMessageOperation;

public interface IGameHandler {

    void OnCreatGame(CommunicatorWebSocketMessageOperation operation , Game gameData);

    void CreatNewGame();
}

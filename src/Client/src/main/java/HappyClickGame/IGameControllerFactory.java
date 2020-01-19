package HappyClickGame;

import Ui.GameController;
import Ui.LobbyController;

public interface IGameControllerFactory {
    GameController getGameController ();
}

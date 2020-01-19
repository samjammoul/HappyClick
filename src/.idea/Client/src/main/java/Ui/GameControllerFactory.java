package Ui;

import HappyClickGame.IGameControllerFactory;

public class GameControllerFactory implements IGameControllerFactory {
    private final static GameController gameController = new GameController();

    public  GameController getGameController() {
        return gameController;
    }
}

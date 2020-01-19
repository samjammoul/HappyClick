package HappyClickGame;

import CommunicatorClient.IGameFactory;

public class GameFactory implements IGameFactory {
    private static final Game game = new Game();



    public  Game getGame() {
       return game;
  }
}

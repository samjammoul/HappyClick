package Ui;

import HappyClickGame.ILobbyControllerFactory;
import HappyClickGame.Lobby;

public class LobbyControllerFactory implements ILobbyControllerFactory {



    private static LobbyController lobbyController = new LobbyController();


    public  LobbyController getLobbyController() {
        return lobbyController;
    }
}

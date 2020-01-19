package HappyClickGame;

import CommunicatorClient.ILobbyFactory;

public class LobbyFactory implements ILobbyFactory {
    private static final Lobby lobby = new Lobby();



    public  Lobby getLobby() {
        return lobby;
    }

}

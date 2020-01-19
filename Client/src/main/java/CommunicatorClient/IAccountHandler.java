package CommunicatorClient;

import HappyClickGame.User;

public interface IAccountHandler {

    User getUserFromLobby();

    void login(String name, String password);
}

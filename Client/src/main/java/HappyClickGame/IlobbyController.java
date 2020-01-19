package HappyClickGame;

import HappyClickGame.Lobby;
import HappyClickGame.User;
import share.PlayerData;
import share.RequestData;

import java.util.List;

public interface IlobbyController {


    void getOnlineUsers(List<User> users);


    void ableSendRequest();

    void requestIsSent(String receiverName);

    void showToUser(String message);

     void closeUi();

     void showUi();

     void showResult(List<PlayerData> playerData);
}

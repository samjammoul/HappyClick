package CommunicatorClient;

import HappyClickGame.Game;
import HappyClickGame.Lobby;
import HappyClickGame.User;
import share.*;

import java.util.List;

public interface Ilobby {
// static mag niet
    // inter face moet in de andere package zijn
    // d van de solid gebruiken om depenciceis

     void setOnlineUsers(List<User> users);
      List<User> getOnlineUsers();

    void setUser(User user);

    void getRequest(RequestData requestData);

    void setRequestStatus(RequestStatus requestStatus);

    void sendInviteToUser(int receiverId );

    void creatNewGame();

    void onCreatGame(CommunicatorWebSocketMessageOperation operation, Game gameData);

    void requestIsSend(RequestData requestData);

    void acceptRequest(RequestData requestData);

    void rejectRequest(RequestData requestData);

     void requestCancel(RequestData requestData);

     void  showToUser(RequestData Response);

    void closeUi();

     void showUi(List<PlayerData> playerData);

   void startGame(QuestionData question);

    Game getGame();

    void setGame(Game game);

}

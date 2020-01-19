package HappyClickGame;

import HappyClickGame.Game;
import HappyClickGame.Lobby;
import HappyClickGame.User;
import share.AnswerData;
import share.RequestData;

public interface ICommunicator {


    /**
     * Start the connection.
     */
    public void start();

    /**
     * Stop the connection.
     */
    public void stop();

    /**
     * Register a property.
     * @param property
     */
    public void register(String name, String password);

    /**
     * Unregister a property.
     * @param property
     */


    void login(String name, String password);

     void creatNewGame (int playernumber , String playerName);

    void sendInviteToUser(User sender , int ReceiverId , int gameId);

    void RequestResponse(RequestData requestData);

    void setLobby(Lobby lobby);

    void sendAnswer(AnswerData answer);
}

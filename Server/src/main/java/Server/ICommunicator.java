package Server;

import share.CommunicatorWebSocketMessage;
import share.CommunicatorWebSocketMessageOperation;
import share.RequestData;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

public interface ICommunicator {

    void notifyAllOnlineUser(List<User> onlineUsers) throws IOException;
    void sendRequestResponse(RequestData requestData, Session senderSession,Session receiverSession) throws IOException;
    void cancelRequest(RequestData requestData, Session senderSession,Session receiverSession) throws IOException;
    void sendScoreToUsers(List<Player> players);
    void sendQuestionToPlayers(Question question,List<Player> players,CommunicatorWebSocketMessageOperation communicatorWebSocketMessageOperation);
     void sendUsers (Session session, CommunicatorWebSocketMessage communicatorWebSocketMessage);

}

package Server;

import com.google.gson.Gson;
import share.CommunicatorWebSocketMessage;

import javax.websocket.Session;
import java.io.IOException;

public class notifyAllOnlineUserThread extends Thread {

    private Session session;
    private CommunicatorWebSocketMessage communicatorWebSocketMessage;
    private Communicator communicator;


   public void setSessionAndMsg(Session session,CommunicatorWebSocketMessage communicatorWebSocketMessage) {
       this.session = session;
       this.communicatorWebSocketMessage = communicatorWebSocketMessage;
   }

    public void run()
    {


            communicator = CommunicatorServerWebSocket.getInstance();
            communicator.sendUsers(session,communicatorWebSocketMessage);



    }
}

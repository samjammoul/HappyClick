package CommunicatorClient;

import HappyClickGame.ICommunicator;
import HappyClickGame.User;
import share.RequestData;

public class CommunicatorRepo {
    private ICommunicator communicator;




    public void sendInviteToUser(User sender , int ReceiverId , int gameId){
        communicator.sendInviteToUser( sender ,  ReceiverId ,  gameId);
    }


    public  void RequestResponse(RequestData requestData){
        communicator.RequestResponse(requestData);
    }
}

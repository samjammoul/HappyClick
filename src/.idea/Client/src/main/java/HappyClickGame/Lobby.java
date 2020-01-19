package HappyClickGame;

import CommunicatorClient.CommunicatorClientWebSocket;
import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;

import Ui.GameControllerFactory;
import Ui.LobbyControllerFactory;
import Ui.RequestUiControllerFactory;
import share.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Lobby implements Ilobby {

 //   public static Lobby classAInstance = new Lobby();

    private static User user;
    private GameFactory gameFactory;
    private  Game game ;
    private static int count;
    private static List<User> onlineUsers;
    private ICommunicator iCommunicator;
    private  static List<RequestData> requestDatalist;
    private ILobbyControllerFactory iLobbyControllerFactory = new LobbyControllerFactory();
    private IlobbyController ilobbyController = iLobbyControllerFactory.getLobbyController();
    private IRequestUiControllerFactory iRequestUiControllerFactory = new RequestUiControllerFactory();
    private IRequestUiController iRequestUiController;
    private static List<IRequestUiController> iRequestUiControllerList;



    public Lobby(){
       onlineUsers = new ArrayList<>();
       requestDatalist = new ArrayList<>();
       iRequestUiControllerList = new ArrayList<>();



    }

    public List<User> getOnlineUsers() {
        return onlineUsers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
       this.game = game;
        ilobbyController.ableSendRequest();
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }




    public void sendInviteToUser(int receiverId ){
        iCommunicator.sendInviteToUser(user,receiverId,game.getGameId());
    }


    public void setOnlineUsers(List<User> users) {

        this.onlineUsers = users;
        ilobbyController.getOnlineUsers(onlineUsers);
        //   iInloggenController.addOnlineUsers(onlineUsers);

        // main.addOnlineUsers(onlineUsers);


    }

  public void getRequest(RequestData request){
      iRequestUiController = iRequestUiControllerFactory.getRequestUiController(this);
      request.setRequestStatus(RequestStatus.Waiting);
      requestDatalist.add(request);
      iRequestUiController.getRequest(request);
      iRequestUiControllerList.add(iRequestUiController);


    }

    @Override
    public void setRequestStatus(RequestStatus requestStatus) {

    }


    public void setRequestStatus(RequestData requestData,RequestStatus requestStatus){
     //   requestData.setRequestStatus(requestStatus);
   }




    public void creatNewGame(){
        if(user.getUserNumber() == 0 ||user.getUserNumber() <0 ){
            System.out.println("No Player registered");
        }else {
            try {
                // iCommunicator.setLobby(this);
                iCommunicator = CommunicatorClientWebSocket.getInstance();

                // Establish connection with server
                iCommunicator.start();
                iCommunicator.creatNewGame(user.getUserNumber(),user.getName());

            }catch (Exception ex ){
                System.out.println(ex);
            }

        }

    }

    public void onCreatGame(CommunicatorWebSocketMessageOperation operation , Game gameData){

        switch (operation) {
            case CreatGameSucess:

                setGame(gameData);

                break;
            case CreatGameNotSucess:
                //  iCreatAccountController.registerfailed();
                break;
            default:
                System.out.println("[ ERROR: Operation || OnCreatGame "  );
                break;
        }
    }

    public void requestIsSend(RequestData requestData){
        ilobbyController.requestIsSent(requestData.getUserReceiver().getName());
    }


    @Override
    public void acceptRequest(RequestData requestData){
        requestData.setRequestStatus(RequestStatus.Accepted);
        iCommunicator = CommunicatorClientWebSocket.getInstance();
        iCommunicator.RequestResponse(requestData);
    }

    @Override
    public void rejectRequest(RequestData requestData){
        requestData.setRequestStatus(RequestStatus.Rejected);
        iCommunicator = CommunicatorClientWebSocket.getInstance();
        iCommunicator.RequestResponse(requestData);
    }

    @Override
    public void requestCancel(RequestData requestData){
        try {
            for (IRequestUiController i:iRequestUiControllerList) {
                if (requestData.getRequestId() == i.getRequestId() && requestData.getGameId() == i.getGameIdOfRequest()){
                    i.closeUi();
                    iRequestUiControllerList.remove(i);
                }

            }
        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void  showToUser(RequestData response){

        if(response.getRequestStatus() == RequestStatus.Rejected){
            ilobbyController.showToUser("Your request is rejected");
        }else if (response.getRequestStatus() == RequestStatus.Accepted){
            ilobbyController.showToUser("Your request is Accepted");
        }else {
            // Ex
        }

    }

    public void closeUi(){
        ilobbyController.closeUi();
    }

    public void showUi(List<PlayerData> playerData){
        ilobbyController.showUi();
        game.closeUi();
        ilobbyController.showResult(playerData);
    }

   public void startGame(QuestionData question){
       closeUi();
      game.startGame(question);


    }
}

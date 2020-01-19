package HappyClickGame;

import CommunicatorClient.CommunicatorClientWebSocket;
import CommunicatorClient.IAccountHandler;
import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import Ui.CreatAccountController;
import Ui.ICreatAccountController;
import Ui.IInloggenController;
import Ui.InloggenController;
import share.CommunicatorWebSocketMessageOperation;
import share.UserData;

public class AccountHandler implements IAccountHandler {

    private ICreatAccountController iCreatAccountController ;
    private   ICommunicator communicator ;
    private   IInloggenController iInloggenController ;
    private ILobbyFactory iLobbyFactory = new LobbyFactory();
    private Ilobby ilobby = iLobbyFactory.getLobby();





    public void registerResponse(CommunicatorWebSocketMessageOperation operation , UserData user){
        iCreatAccountController = new CreatAccountController();
        switch (operation) {
            case RegisterSuccess:

                iCreatAccountController.registerSucceed();
                ilobby.setUser(new User(user.getUserId(),user.getName()));

                break;
            case RegisterNotSuccess:
                iCreatAccountController.registerfailed();
                break;
            default:
                System.out.println("[ ERROR: Operation"  );
                break;
        }
    }
    public void loginResponse(CommunicatorWebSocketMessageOperation operation, UserData user){
        iInloggenController = new InloggenController();
        switch (operation) {
            case loginSuccess:
                ilobby.setUser(new User(user.getUserId(),user.getName()));
                iInloggenController.loginSucceed();

                break;
            case logInNotSuccess:
                iInloggenController.loginfailed();
                break;
            default:
                System.out.println("[ ERROR: Operation"  );
                break;
        }
    }
    public void login(String name, String password){

        communicator = CommunicatorClientWebSocket.getInstance();

        // Establish connection with server
        communicator.start();
        communicator.login(name,password);
    }

    public User getUserFromLobby(){
       return ilobby.getUser();
    }
}

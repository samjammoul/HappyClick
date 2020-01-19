import CommunicatorClient.IAccountHandler;
import CommunicatorClient.ILobbyFactory;
import CommunicatorClient.Ilobby;
import HappyClickGame.AccountHandler;
import HappyClickGame.LobbyFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountHandlerTest {
     private IAccountHandler iAccountHandler =  new AccountHandler();
    private ILobbyFactory lobbyFactory = new LobbyFactory();
    private Ilobby ilobby = lobbyFactory.getLobby();

    @Test
    void login_checkUsernamePassword_setTheUserDataInLobby() {

       /// int expectId = 1;

    //    iAccountHandler.login("sam","jammoul");

   //     int userId = iAccountHandler.getUserFromLobby().getUserNumber();

  //      assertEquals(expectId,userId);



    }


}
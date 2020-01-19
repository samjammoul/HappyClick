package Server;

import org.junit.jupiter.api.Test;
import share.UserData;

import javax.websocket.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountHandlerTest {
    private Session session = null;
    private UserHandler userHandler = new UserHandler();
    private AccountHandler accountHandler;

/*
    // Test with correct Answer (Test rest api and database connection)
    @Test
    void login_checkIfRestReturnIdGreaterThen0_CorrectScoreIsSet() throws Exception {
        int expectUserId = 24;

        accountHandler = new AccountHandler(userHandler);
        UserData userData = accountHandler.login(new UserData("ee","rr"),session);

        assertEquals(expectUserId,userData.getUserId());

    }

 */
}

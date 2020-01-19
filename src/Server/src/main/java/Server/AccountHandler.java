package Server;

import rest.HappyClickRestClient;
import share.UserData;

import javax.websocket.Session;

public class AccountHandler {
    private HappyClickRestClient happyClickRestClient  ;
    private UserHandler userHandler;

    public AccountHandler(UserHandler userHandler){
        happyClickRestClient = new HappyClickRestClient();
        this.userHandler = userHandler;
    }



    public UserData registerUser(UserData user , Session session) throws Exception {


        UserData userResponse;

      int  userId =   happyClickRestClient.newUser(user.getName(),user.getPassword());
        if(userId > 0){
            try {
                userResponse = new UserData(userId,user.getName());
                userHandler.addUserToOnlineList(userResponse, session);
                return userResponse;
            }catch (Exception ex  ){
                System.out.println(ex);
                return new UserData(-1);
            }



        }else {
            userResponse = new UserData(-1);
            return userResponse;
        }



    }
    public UserData login(UserData user , Session session) throws Exception {

        User userResponseRest;
        UserData userResponse;
        userResponseRest =   happyClickRestClient.login(user.getName(),user.getPassword());
        if(userResponseRest.getUserNumber() > 0){
            try {
                userResponse = new UserData(userResponseRest.getUserNumber(),userResponseRest.getName());
                userHandler.addUserToOnlineList(userResponse, session);
                return userResponse;
            }catch (Exception ex  ){
                System.out.println(ex);
                return new UserData(-1);
            }



        }else {
            userResponse = new UserData(-1);
            return userResponse;
        }


    }
}

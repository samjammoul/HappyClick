package DataBase.Context;

import Models.User;

import java.util.List;

public interface IUserContext {

    public  int RegisterUser(String userName , String userPassword );

    List<User> getallUsers( );

   void getallOnlineUsers();

}

package DataBase.Repo;

import DataBase.Context.IUserContext;
import Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

  private static List<User> onlineUsers = new ArrayList<>();

    private IUserContext context;


    public UserRepo(IUserContext context)
    {
        this.context = context;

    }
    public UserRepo( )
    {

    }

    public void addUser(User user){
        onlineUsers.add(user);
    }

    public  int RegisterUser(String userName , String userPassword ){

       return context.RegisterUser( userName ,  userPassword );

    }

    public List<User> getallUsers(){

        return context.getallUsers();
    }

    public void getallOnlineUsers(){
        context.getallOnlineUsers();
    }

    public List<User> getOnlineUsers(){
        return onlineUsers;
    }

}

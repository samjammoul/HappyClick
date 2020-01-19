package Server;

import share.UserData;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {
    private static List<User> onlineUsers = new ArrayList<>();
    private Communicator communicator;



    public void addUserToOnlineList(UserData userData, Session session) throws IOException {
        communicator = CommunicatorServerWebSocket.getInstance();
        onlineUsers.add(new User (userData.getUserId(),userData.getName(),session));
        communicator.notifyAllOnlineUser(getOnlineUsers());
    }

    public List<User> getOnlineUsers() {
        return onlineUsers;
    }

    public void setOnlineUsers(List<User> onlineUsers) {
        this.onlineUsers = onlineUsers;
    }

    public User checkIfOnlineUser(User user){

        for (User u:onlineUsers
             ) {
            if (u.getUserNumber() == user.getUserNumber()){
                user = u ;
                return user;
            }
        }

        return new User(-1);
    }

    public Session getSessionOfUser(int userId){
        Session session = null;
        for (User u:onlineUsers) {
            if(u.getUserNumber() == userId){
                session =u.getSession();
                return session ;

            }

        }
        return session;
    }
}

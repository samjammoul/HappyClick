package Server;

import javax.websocket.Session;

public class User {
    private int userNumber;
    private String name;
    private String password;
    private Session session;


    public User(int userNumber, String name, String password) {
        this.userNumber = userNumber;
        this.name = name;
        this.password = password;

    }

    public User(int userNumber, String name) {
        this.userNumber = userNumber;
        this.name = name;

    }
    public User(int userNumber, String name,Session session) {
        this.userNumber = userNumber;
        this.name = name;
         this.session = session;
    }

    public User(String name) {
        this.name = name;

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;

    }
    public User(int userNumber) {
        this.userNumber = userNumber;


    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPlayerNumber(int playerNumber) {
        this.userNumber = playerNumber;
    }

    public int getUserNumber() {
        return this.userNumber;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

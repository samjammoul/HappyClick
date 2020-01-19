package Models;

import share.UserStatus;

public class User {
    private int userNumber;
    private String name;
    private String password;
    private String[] callbacks;
    private UserStatus status;

    public User(int userNumber, String name, String password) {
        this.userNumber = userNumber;
        this.name = name;
        this.password = password;
        this.updateCallbacks();
    }

    public User(int userNumber, String name) {
        this.userNumber = userNumber;
        this.name = name;
        this.updateCallbacks();
    }

    public User(String name) {
        this.name = name;
        this.updateCallbacks();
    }

    public User(int userNumber, String name, String password,UserStatus status ) {
        this.userNumber = userNumber;
        this.name = name;
        this.password = password;
        this.status = status;
        this.updateCallbacks();
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.updateCallbacks();
    }

    public void updateCallbacks() {
        this.callbacks = new String[]{"/users/delete/" + this.name, "/users/update/" + this.name};
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
}

package HappyClickGame;

import share.UserStatus;

public class Player {

    private int playerNumber;
    private String name;
    private UserStatus status;

    public Player(int userNumber, String name) {
        this.playerNumber = userNumber;
        this.name = name;
    }

    public int getPlayerNumber() {
        return this.playerNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getUserstatus() {
        return this.status;
    }

    public void setUserstatus(UserStatus status) {
        this.status = status;
    }

    public void setPlayerNumber(int userNumber) {
        this.playerNumber = userNumber;
    }

    public String getName() {
        return this.name;
    }


}

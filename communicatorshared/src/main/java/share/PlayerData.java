package share;

public class PlayerData {

    private int playerNumber;
    private String name;
    private UserStatus status;
    private int score;


    public PlayerData(int userNumber, String name) {
        this.playerNumber = userNumber;
        this.name = name;
    }
    public PlayerData(int playerNumber,String name,int score){
        this.playerNumber = playerNumber;
        this.score = score;
        this.name = name;
    }


    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

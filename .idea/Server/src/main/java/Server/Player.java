package Server;

import javax.websocket.Session;

public class Player {
    private Session session;
    private int playerNr;
    private String name;
    private int score;


    public Player(int playerNr, String name, Session session) {
        this.session = session;
        this.playerNr = playerNr;
        this.name = name;
    }

    public Session getSession() {
        return session;
    }
    public Session setSession(Session session) {
        return session;
    }
    public int getPlayerNr() {
        return playerNr;
    }

    public String getName() {
        return name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }
}

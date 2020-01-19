package share;

import java.util.ArrayList;
import java.util.List;

public class GameData {

    private int gameId;


    List<PlayerData> players = new ArrayList<>();
    List<RequestData> requestDataList = new ArrayList<>();

    public GameData( int gameId){

     this.gameId = gameId;
    }


    public int getGameId() {
        return gameId;
    }

    public List<PlayerData> getPlayers(){
        return players;
    }

    public void addRequest(RequestData requestData){
        requestDataList.add(requestData);
    }

    public int addPalyer(PlayerData playerData){
        if(players.size() < 4) {
            players.add(playerData);
            return 1;
        }
        return -1;
    }
}

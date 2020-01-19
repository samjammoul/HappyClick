package share;

public class RequestData {

    private int requestId;
    private UserData userSender;

    private UserData userReceiver;

    private RequestStatus requestStatus;

    private int gameId;

    public RequestData (UserData userSender,UserData userReceiver,int gameId){
        this.userSender = userSender;
        this.userReceiver =userReceiver;
        this.gameId = gameId;

    }
    public RequestData (UserData userSender,int userReceiverId,int gameId){
        this.userSender = userSender;
        userReceiver = new UserData(userReceiverId) ;
        this.gameId = gameId;

    }

    public RequestData(int requestId){
        this.requestId = requestId;
    }

    public RequestData (RequestStatus requestStatus){
        this.requestStatus = requestStatus;
    }



    public UserData getUserReceiver() {
        return userReceiver ;
    }

    public void setUserReceiver(UserData userReceiver) {
        this.userReceiver = userReceiver;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public UserData getUserSender() {
        return userSender;
    }

    public void setUserSender(UserData userSender) {
        this.userSender = userSender;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }


}

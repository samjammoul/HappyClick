package CommunicatorClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.nio.file.FileSystemLoopException;
import java.util.List;

import HappyClickGame.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import share.*;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public  class CommunicatorClientWebSocket extends Communicator  {

    // Singleton
    private static CommunicatorClientWebSocket instance = null;

    /**
     * The local websocket uri to connect to.
     */
    private final String uri = "ws://localhost:8080/communicator/";

    private Session session;

    private String message;

    private Gson gson = null;

    private AccountHandler accountHandler = new AccountHandler();
    private ILobbyFactory lobbyFactory = new LobbyFactory();
    private Ilobby ilobby = lobbyFactory.getLobby();
    private IGame iGame ;




    // Status of the webSocket client
    boolean isRunning = false;

    // Private constructor (singleton pattern)
    public CommunicatorClientWebSocket() {
        gson = new Gson();
    }

    /**
     * Get singleton instance of this class.
     * Ensure that only one instance of this class is created.
     * @return instance of client web socket
     */
    public static CommunicatorClientWebSocket getInstance() {
        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new CommunicatorClientWebSocket();
        }
        return instance;
    }

    /**
     *  Start the connection.
     */
    @Override
    public void start() {
        System.out.println("[WebSocket Client start connection]");
        if (!isRunning) {
            startClient();
            isRunning = true;
        }
    }

    @Override
    public void stop() {
        System.out.println("[WebSocket Client stop]");
        if (isRunning) {
            stopClient();
            isRunning = false;
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        System.out.println("[WebSocket Client open session] " + session.getRequestURI());
        this.session = session;

    }

    @OnMessage
    public void onWebSocketText(String message, Session session) throws Exception {
        this.message = message;
        System.out.println("[WebSocket Client message received] " + message);

        handleMessageFromClient(message, session);
;
    }
   @Override
   public void setLobby(Lobby lobby){

    }
/*
    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        System.out.println("[WebSocket Client connection error] " + cause.toString());
    }

 */

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        System.out.print("[WebSocket Client close session] " + session.getRequestURI());
        System.out.println(" for reason " + reason);
        session = null;
    }

    @Override
    public void register(String name, String password) {
        UserData userdata = new UserData(-1,name,password);
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(CommunicatorWebSocketMessageOperation.REGISTERPROPERTY);
        message.setData(new Gson().toJson(userdata));
        sendMessageToServer(message);
    }

    @Override
    public void login(String name, String password) {

        UserData userdata = new UserData(0,name,password);
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(CommunicatorWebSocketMessageOperation.logInProperty);
        message.setData(new Gson().toJson(userdata));
        sendMessageToServer(message);
    }

    @Override
    public void creatNewGame (int playernumber , String playerName) {
        PlayerData playerData = new PlayerData(playernumber, playerName);
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(CommunicatorWebSocketMessageOperation.CreatGame);
        message.setData(new Gson().toJson(playerData));
        sendMessageToServer(message);
    }

    @Override
    public void sendInviteToUser(User sender , int ReceiverId , int gameId){
   try {
            RequestData requestData = new RequestData(new UserData(sender.getUserNumber(),sender.getName()), ReceiverId,gameId);
            CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
            message.setOperation(CommunicatorWebSocketMessageOperation.SendRequest);
            message.setData(new Gson().toJson(requestData));
            sendMessageToServer(message);
     }catch (Exception ex ){
          System.out.println(ex);
     }

    }

    public void sendAnswer(AnswerData answer){

        try {

            CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
            message.setOperation(CommunicatorWebSocketMessageOperation.EnteredAnswer);
            message.setData(new Gson().toJson(answer));
            sendMessageToServer(message);
        }catch (Exception ex ){
            System.out.println(ex);
        }

    }


    private void sendMessageToServer(CommunicatorWebSocketMessage message) {
        String jsonMessage = gson.toJson(message);
        // Use asynchronous communication
        session.getAsyncRemote().sendText(jsonMessage);
    }

    /**
     * Get the latest message received from the websocket communication.
     * @return The message from the websocket communication
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message, but no action is taken when the message is changed.
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Start a WebSocket client.
     */
    private void startClient() {
        System.out.println("[WebSocket Client start]");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        } catch (Exception ex) {
            // do something useful eventually
            ex.printStackTrace();
        }
    }

    /**
     * Stop the client when it is running.
     */
    private void stopClient(){
        System.out.println("[WebSocket Client stop]");
        try {
            session.close();

        } catch (IOException ex){
            // do something useful eventually
            ex.printStackTrace();
        }
    }
    @Override
    public void RequestResponse(RequestData requestData) {

        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(CommunicatorWebSocketMessageOperation.RequestResponse);
        message.setData(new Gson().toJson(requestData));
        sendMessageToServer(message);
    }




    private void handleMessageFromClient(String jsonMessage, Session session) throws Exception {


        CommunicatorWebSocketMessage wbMessage = null;
        try {
            wbMessage = gson.fromJson(jsonMessage,CommunicatorWebSocketMessage.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket ERROR: cannot parse Json message " + jsonMessage);
            return;
        }

        // Operation defined in message
        CommunicatorWebSocketMessageOperation operation;
        operation = wbMessage.getOperation();

        // Process message based on operation


            switch (operation) {
                case RegisterNotSuccess:
                case RegisterFailed:
                case RegisterSuccess:
                    // Register property if  registered

                       System.out.println("start!");
                       try {
                           UserData user = gson.fromJson(wbMessage.getData(), UserData.class);
                            accountHandler.registerResponse(operation ,user);

                        //   iCreatAccountController.registerSucceed();
                       }catch (Exception ex){
                           System.out.println(ex);
                       }

                        System.out.println("Done!");

                    break;
                case logInNotSuccess:
                case loginSuccess:
                    // Register property if  registered
                    System.out.println("start!");
                    try {
                        UserData user = gson.fromJson(wbMessage.getData(), UserData.class);
                        accountHandler.loginResponse(operation,user);
                        //   iCreatAccountController.registerSucceed();
                    }catch (Exception ex){
                        System.out.println("loginResponse");
                        System.out.println(ex);
                    }

                    System.out.println("Done!");

                    break;
                case CreatGameSucess:
                case CreatGameNotSucess:

                  GameData gameData = gson.fromJson(wbMessage.getData(), GameData.class);

                    ilobby.onCreatGame(operation,new Game(gameData.getGameId()));


                    break;
                case OnlineUsers:
                    // try {

                    //  Lobby lobby = new Lobby();
                    Type listType = new TypeToken<List<User>>(){}.getType();
                    List<User>  users = gson.fromJson(wbMessage.getData(), listType);

                    ilobby.setOnlineUsers(users);



                    //  }catch (Exception ex ){
                    //       System.out.println(ex);
                    //     }

                    break;
                case GetRequest:
                    try {
                        RequestData requestData = gson.fromJson(wbMessage.getData(), RequestData.class);
                        ilobby.getRequest(requestData);
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                    break;
                case GameIsFull:
                    try {
                        // send to lobby
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                    break;
                case RequestIsSent:
                    RequestData requestData = gson.fromJson(wbMessage.getData(), RequestData.class);
                    ilobby.requestIsSend(requestData);
                    break;
                case RequestCancel:
                  RequestData request = gson.fromJson(wbMessage.getData(), RequestData.class);
                    ilobby.requestCancel(request);
                    break;
                case RequestRejected:
                case RequestAccepted:
                    RequestData Response = gson.fromJson(wbMessage.getData(), RequestData.class);
                    ilobby.showToUser(Response);
                    break;
                case GetQuestion:
                    QuestionData questionData = gson.fromJson(wbMessage.getData(), QuestionData.class);
                    iGame = ilobby.getGame();
                    iGame.getQuestion( questionData);
                    break;
                case StartGame:
                    QuestionData question = gson.fromJson(wbMessage.getData(), QuestionData.class);
                    ilobby.startGame(question);

                    break;
                case GetResult:
                    Type list = new TypeToken<List<PlayerData>>(){}.getType();
                    List<PlayerData>  playersData = gson.fromJson(wbMessage.getData(), list);
                    ilobby.showUi(playersData);
                    break;
                case GetGameInformation:
                    RequestData requestinfo = gson.fromJson(wbMessage.getData(), RequestData.class);
                    ilobby.setGame(new Game(requestinfo.getGameId()));
                    break;

                default:
                    System.out.println("[WebSocket ERROR: cannot process Json message " + jsonMessage);
                    break;
            }
        }


}

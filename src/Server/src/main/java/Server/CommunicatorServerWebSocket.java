package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import share.*;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import static share.CommunicatorWebSocketMessageOperation.*;


@ServerEndpoint(value="/communicator/")
public class CommunicatorServerWebSocket extends Communicator {

    // All sessions
    // Singleton
    private static CommunicatorServerWebSocket instance = null;
    private static final List<Session> sessions = new ArrayList<>();
  //  HappyClickRestClient happyClickRestClient = new HappyClickRestClient();
    private  Gson gson   =  new GsonBuilder().setPrettyPrinting().create();
    final static UserHandler userHandler = new UserHandler();
    final static AccountHandler accountHandler = new AccountHandler(userHandler);
    final static GameHandler gameHandler = new GameHandler();
    private notifyAllOnlineUserThread notifyThread ;



    // Map each property to list of sessions that are subscribed to that property
  //  private static final Map<String,List<Session>> propertySessions = new HashMap<>();

    public static CommunicatorServerWebSocket getInstance() {

        if (instance == null) {
            System.out.println("[WebSocket Client create singleton instance]");
            instance = new CommunicatorServerWebSocket();
        }
        return instance;
    }

    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());



    }

    @OnMessage
    public void onText(String message, Session session) throws Exception {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);
        CommunicatorWebSocketMessage websocketMessage = new Gson().fromJson(message, CommunicatorWebSocketMessage.class);
        handleMessageFromClient(websocketMessage, session);
       // CommunicatorWebSocketMessage websocketMessage = new Gson().fromJson(message, CommunicatorWebSocketMessage.class);


       // websocketMessage.setProperty(CommunicatorWebSocketMessageOperation.REGISTERPROPERTY);
      //  String jsonMessage = gson.toJson(message);
  //      session.getBasicRemote().sendText(new Gson().toJson(msg));
        //session.getAsyncRemote().sendText(new Gson().toJson(websocketMessage));
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(CommunicatorWebSocketMessage websocketMessage, Session session) throws Exception {

        // Operation defined in message
        CommunicatorWebSocketMessageOperation operation;
        operation = websocketMessage.getOperation();

        // Process message based on operation

            switch (operation) {
                case REGISTERPROPERTY:
               onRegisterUserMessage(websocketMessage,session);
                    break;

                case logInProperty:
                    onLogInMessage(websocketMessage,session);
                    break;

                case CreatGame:
                    // Subsribe to property if the property has been registered
                   onCreatGame(websocketMessage,session);
                    break;

                case SendRequest:
                    onRequest(websocketMessage,session);
                    break;
                case RequestResponse:
                    onRequestResponse(websocketMessage,session);
                    break;
                case EnteredAnswer:
                    onGetAnswer(websocketMessage,session);
                    break;
                default:
                    System.out.println("[WebSocket ERROR: cannot process Json message " + websocketMessage);
                    break;
            }
        }


    private void onRegisterUserMessage(CommunicatorWebSocketMessage websocketMessage, Session session) throws Exception {

        try {
            UserData user = new Gson().fromJson(websocketMessage.getData(), UserData.class);
            UserData userResponse = accountHandler.registerUser(user,session);
            if(userResponse.getUserId() > 0){


                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(CommunicatorWebSocketMessageOperation.RegisterSuccess);
                UserData userData = new UserData(userResponse.getUserId(),userResponse.getName());
                //  onlineUsers.add(new User(userId,user.getName(),session));

                message.setData(new Gson().toJson(userData));
                // message.setData(new Gson().toJson(user));
                //  String msg = gson.toJson(message);
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }else {
                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(CommunicatorWebSocketMessageOperation.RegisterFailed);
                // message.setData(new Gson().toJson(user));
                //  String msg = gson.toJson(message);
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }
        } catch (Exception e) {
            CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
            msg.setOperation(RegisterNotSuccess);
            session.getBasicRemote().sendText(new Gson().toJson(msg));
            return;
        }




        //  session.getAsyncRemote().sendText(new Gson().toJson(message));
    }

    private void onLogInMessage(CommunicatorWebSocketMessage websocketMessage, Session session) throws IOException {

        try {
            UserData user = new Gson().fromJson(websocketMessage.getData(), UserData.class);
            UserData userResponse = accountHandler.login(user,session);
            if(userResponse.getUserId() > 0){


                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(loginSuccess);
                UserData userData = new UserData(userResponse.getUserId(),userResponse.getName());
                //  onlineUsers.add(new User(userId,user.getName(),session));

                message.setData(new Gson().toJson(userData));
                // message.setData(new Gson().toJson(user));
                //  String msg = gson.toJson(message);
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }else {
                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(logInNotSuccess);
                // message.setData(new Gson().toJson(user));
                //  String msg = gson.toJson(message);
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }
        } catch (Exception e) {
            CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
            msg.setOperation(logInNotSuccess);
            session.getBasicRemote().sendText(new Gson().toJson(msg));

        }



    }

    private void onCreatGame(CommunicatorWebSocketMessage websocketMessage, Session session) throws Exception {

        PlayerData player = new Gson().fromJson(websocketMessage.getData(), PlayerData.class);

        try {

            GameData gameData = gameHandler.creatGame(player,session);
            if(gameData.getGameId()>0){
                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(CreatGameSucess);

                message.setData(new Gson().toJson(gameData));
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }else {
                CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
                msg.setOperation(CreatGameNotSucess);
                session.getBasicRemote().sendText(new Gson().toJson(msg));

            }

        } catch (Exception e) {
            CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
            msg.setOperation(CreatGameNotSucess);
            session.getBasicRemote().sendText(new Gson().toJson(msg));

        }



    }


@Override
    public  void notifyAllOnlineUser(List<User> onlineUsers) throws IOException {
        List<User> users = new ArrayList<>();
        users.clear();
        for (User u:onlineUsers
             ) {
            users.add(new User(u.getUserNumber(),u.getName()));
        }
         System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"+  " Start" );
        CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
        msg.setOperation(OnlineUsers);
        msg.setData(gson.toJson(users));
        for (Session s:sessions) {

            s.getAsyncRemote().sendText(new Gson().toJson(msg));
        }
    }

    private void onRequest(CommunicatorWebSocketMessage websocketMessage, Session session) throws Exception {
        User receiver = null;
        RequestData requestData = new Gson().fromJson(websocketMessage.getData(), RequestData.class);

        try {
            Map<RequestData,Session> requestDataSessionMap = gameHandler.sendRequest(requestData);
            if(requestDataSessionMap.isEmpty()){
                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(SendRequestNotSucess);
                message.setData(new Gson().toJson(requestData));
                session.getBasicRemote().sendText(new Gson().toJson(message));
            }else {
                for (Map.Entry<RequestData,Session> entry: requestDataSessionMap.entrySet()) {
                    requestData = entry.getKey();
                    receiver = new User(requestData.getUserReceiver().getUserId(),requestData.getUserReceiver().getName(),entry.getValue());
                    break;
                }

                CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                message.setOperation(GetRequest);
                message.setData(new Gson().toJson(requestData));
                receiver.getSession().getBasicRemote().sendText(new Gson().toJson(message));

                gameHandler.requestIsSent(requestData,receiver.getSession());

                CommunicatorWebSocketMessage message2 = new CommunicatorWebSocketMessage();
                message.setOperation(RequestIsSent);
                message.setData(new Gson().toJson(requestData));
                session.getBasicRemote().sendText(new Gson().toJson(message));

            }
        } catch (Exception e) {
            CommunicatorWebSocketMessage msg = new CommunicatorWebSocketMessage();
            msg.setOperation(SendRequestNotSucess);
            session.getBasicRemote().sendText(new Gson().toJson(msg));
            return;
        }




    }

    private void onRequestResponse(CommunicatorWebSocketMessage websocketMessage, Session session) throws Exception {

            try {
                RequestData requestData = new Gson().fromJson(websocketMessage.getData(), RequestData.class);
                switch (requestData.getRequestStatus()) {
                    case Rejected:
                    case Accepted:

                        gameHandler.requestResponse(requestData);
                      break;
                    default:
                        System.out.println("[WebSocket ERROR || onRequestResponse: cannot process Json message " + websocketMessage);
                        break;
                }

            } catch (Exception e) {
                System.out.println("[WebSocket ERROR || onRequestResponse || onRequestResponse: cannot process Json message " + websocketMessage);
                return;
            }
    }




    public void sendRequestResponse(RequestData requestData,Session senderSession,Session receiverSession) throws IOException {
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        switch (requestData.getRequestStatus()) {
            case Rejected:

                message.setOperation(RequestRejected);

                break;

            case Accepted:

                message.setOperation(RequestAccepted);
                sendInformationToRequestReceiver( requestData, receiverSession);
                break;


            default:
                System.out.println("[WebSocket ERROR: cannot process Json message " + " || sendRequestResponse");
                break;
        }
        message.setData(new Gson().toJson(requestData));
        senderSession.getBasicRemote().sendText(new Gson().toJson(message));
    }

    public void sendInformationToRequestReceiver(RequestData requestData,Session receiverSession) throws IOException {
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(GetGameInformation);
        message.setData(new Gson().toJson(requestData));
        receiverSession.getBasicRemote().sendText(new Gson().toJson(message));
    }

    public void cancelRequest(RequestData requestData, Session senderSession,Session receiverSession) throws IOException {
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(RequestRejected);
        message.setData(new Gson().toJson(requestData));
        senderSession.getBasicRemote().sendText(new Gson().toJson(message));

        CommunicatorWebSocketMessage message2 = new CommunicatorWebSocketMessage();
        message.setOperation(RequestCancel);
        message.setData(new Gson().toJson(requestData));
        receiverSession.getBasicRemote().sendText(new Gson().toJson(message2));
    }

    public void sendQuestionToPlayers(Question question,List<Player> players,CommunicatorWebSocketMessageOperation communicatorWebSocketMessageOperation){
        try {
            List<AnswerData> answerDataList = new ArrayList<>();
            for (Answer answer:question.getAnswers()) {
                answerDataList.add(new AnswerData(answer.getAnswerId(),answer.getAnswerText(),answer.getAnswerId()));
            }
            CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
            message.setOperation(communicatorWebSocketMessageOperation);
            QuestionData questionData = new QuestionData(question.getQuestionId(),question.getTextQuestion(),question.getPicName(),answerDataList,question.getGameId());
            message.setData(new Gson().toJson(questionData));
            for (Player player : players) {
                player.getSession().getBasicRemote().sendText(new Gson().toJson(message));
            }
        }catch (Exception ex){
            System.out.println(ex);
        }


    }

    public void sendScoreToUsers(List<Player> players){
        try {
      List<PlayerData> scoreData = new ArrayList<>();
        for (Player player:players
             ) {
            scoreData.add(new PlayerData(player.getPlayerNr(),player.getName(),player.getScore()));
        }
        CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
        message.setOperation(GetResult);
        message.setData(new Gson().toJson(scoreData));
        for (Player player : players) {
            player.getSession().getBasicRemote().sendText(new Gson().toJson(message));
        }
    }catch (Exception ex){
        System.out.println(ex);
    }
    }

    public void  onGetAnswer(CommunicatorWebSocketMessage websocketMessage, Session session){
    //    try {
            AnswerData answerData = new Gson().fromJson(websocketMessage.getData(), AnswerData.class);
            gameHandler.getPlayerAnswer(answerData);

    //    }catch (Exception ex){
       //     System.out.println(ex);
    //   }

    }
    @Override
    public synchronized void sendUsers (Session session, CommunicatorWebSocketMessage communicatorWebSocketMessage) {
        try {
            session.getBasicRemote().sendText(new Gson().toJson(communicatorWebSocketMessage));
        }catch (Exception ex ){

        }

    }


}





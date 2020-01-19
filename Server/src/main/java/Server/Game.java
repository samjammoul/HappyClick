package Server;

import share.CommunicatorWebSocketMessageOperation;
import share.RequestData;
import share.RequestStatus;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private int gameId;

   private List<Player> players = new ArrayList<>();
   private List<RequestData> requestDataList = new ArrayList<>();
   private List<Question> questionList = new ArrayList<>();
   private int time;
   private Communicator communicator;
   private static UserHandler userHandler = new UserHandler();
   private Set set = new Set();
   private Timer timer = new Timer();



    public void setUpGame(Player player){
            players.add(player);

    }

    public Game (int gameId){
        this.gameId = gameId;
}
    public Game ( ){
    }
    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId= gameId ;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void addPlayer(Player player) throws IOException {
        if(checkPlayersNumber()){
            players.add(player);
            if(players.size() == 2){ // 4
                startGame();
            }
        }else {
            // do thing...
        }
    }

    public boolean checkPlayersNumber(){
        if(players.size() < 4){
            return true;
        }else {
            return false;
        }
    }

    public int addRequest(RequestData requestData){
        try {
            int requestId;
            //   if(requestDataList.size() <= 4){   // think about it
            requestDataList.add(requestData);;
            requestData.setRequestId( requestId =requestDataList.size()+1);
            return requestId;
        }catch (Exception ex){
            System.out.println(ex);
            return -1;

        }



    }

    public void setTimerForRequest(RequestData requestData,Session session){
        RequestData request = getRequest(requestData.getRequestId());
        if(request.getRequestId()>0){
            Timer timer = new Timer();
            TimerTask task;
            timer.schedule(task= new TimerTask() {



                @Override
                public void run() {
                    RequestData request = getRequest(requestData.getRequestId());
                    if (request.getRequestStatus() == RequestStatus.Accepted ||request.getRequestStatus() == RequestStatus.Rejected ){
                        timer.cancel();
                        time = 0;
                        try {
                            if(request.getRequestStatus() == RequestStatus.Accepted ){
                                addPlayer(new Player(requestData.getUserReceiver().getUserId(),requestData.getUserReceiver().getName(),session));
                            }
                            sendRequestResponse(request);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if(time == 30){
                        timer.cancel();
                        time = 0;
                        request.setRequestStatus(RequestStatus.cancel);
//                      send to sender...
                        try {
                            cancelRequest(request);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, 1,1*1000);
        }
    }

    public void sendRequestResponse(RequestData requestData) throws IOException {
        if(userHandler.getSessionOfUser(requestData.getUserSender().getUserId()) != null && userHandler.getSessionOfUser(requestData.getUserReceiver().getUserId())!= null){
            Session senderSession = userHandler.getSessionOfUser(requestData.getUserSender().getUserId());
            Session receiverSession = userHandler.getSessionOfUser(requestData.getUserReceiver().getUserId());
            communicator = CommunicatorServerWebSocket.getInstance();
            communicator.sendRequestResponse(requestData,senderSession,receiverSession);


        }else {
            // Send error
        }

    }

    public void cancelRequest(RequestData requestData) throws IOException {
        if(userHandler.getSessionOfUser(requestData.getUserSender().getUserId()) != null){
            Session senderSession = userHandler.getSessionOfUser(requestData.getUserSender().getUserId());
            Session receiverSession = userHandler.getSessionOfUser(requestData.getUserReceiver().getUserId());
            communicator = CommunicatorServerWebSocket.getInstance();
            communicator.cancelRequest(requestData,senderSession,receiverSession);
        }else {
            // Send error
        }

    }

    public RequestData getRequest(int requestId){
        for (RequestData request:requestDataList) {
            if(request.getRequestId() == requestId){
                return request;
            }
        }
        return new RequestData(-1);
    }

    public void changeRequestStatus(int requestId,RequestStatus status){
        try {

            RequestData requestData = getRequest(requestId);
            requestData.setRequestStatus(status);
            for (RequestData request: requestDataList) {
                if(request.getRequestId() == requestData.getRequestId()){
                    request = requestData;
                    break;
                }
            }

        }catch (Exception ex){
            System.out.println(ex);
        }

    }

    public void startGame() throws IOException {

      set.setUpQuestions(this);
        TimerTask task;

        timer.schedule(task= new GameHelper(this), 0,10*1000); // 5 second


    }

    public void addQuestion(Question question){
        questionList.add(question);
    }

    public int getQuestionsListSize(){
        return questionList.size();
    }

    public void sendQuestionToPlayer(int questionNumber){

       Question question =  questionList.get(questionNumber);
       question.setGameId(gameId);
        communicator = CommunicatorServerWebSocket.getInstance();
        communicator.sendQuestionToPlayers(question,players,CommunicatorWebSocketMessageOperation.GetQuestion);

    }

    public void sendToPlayersToStartTheGame(int questionNumber){
        Question question =  questionList.get(questionNumber);
        question.setGameId(gameId);
        communicator = CommunicatorServerWebSocket.getInstance();
        communicator.sendQuestionToPlayers(question,players,CommunicatorWebSocketMessageOperation.StartGame);
    }

    public void gameIsFinish(){
        timer.cancel();
        checkAnswerSetScore();
        setPlayersScore();
        sendScoreToUsers();

    }

    public void checkAnswerSetScore(){

        for (Question q:questionList) {
            int correctAnswerNumber = 0;
            if (q.getEnteredAnswers() == null){
                // set score of players ZerO
            }else {
                for (EnteredAnswer enteredAnswer : q.getEnteredAnswers()){


                    if(enteredAnswer.getAnswerId()== q.getCorrectAnswer().getAnswerId()){
                        int indexnumber = q.getEnteredAnswers().indexOf(enteredAnswer);
                        if(correctAnswerNumber == 0 ){

                            q.getEnteredAnswers().get(indexnumber).setAnswerScore(10);
                            correctAnswerNumber +=1;

                        }else if(correctAnswerNumber == 1 ){
                            q.getEnteredAnswers().get(indexnumber).setAnswerScore(8);
                            correctAnswerNumber +=1;

                        }else if(correctAnswerNumber == 2 ){
                            q.getEnteredAnswers().get(indexnumber).setAnswerScore(6);
                            correctAnswerNumber +=1;

                        }else if(correctAnswerNumber == 3 ){
                            q.getEnteredAnswers().get(indexnumber).setAnswerScore(4);
                            correctAnswerNumber +=1;

                        }
                    }else {
                        enteredAnswer.setAnswerScore(0);
                    }
                }
            }
        }

    }

    public void setPlayersScore(){
        for (Question question  :  questionList){
            List<EnteredAnswer> answerList = question.getEnteredAnswers();
            for (Player player : players){
                for (EnteredAnswer enteredAnswer : answerList){
                     if (player.getPlayerNr() == enteredAnswer.getPlayerNumber()){
                         player.setScore(enteredAnswer.getAnswerScore());
                     }
                }
            }
        }
        // 1- send the score to each user !! show the Result to user
        // 2- setup the send question to user with changing in ui
        //3- in client side send the answer to server

    }

    public void sendScoreToUsers(){
        communicator = CommunicatorServerWebSocket.getInstance();
        communicator.sendScoreToUsers(players);


    }
    public Question getQuestionId(int questionId){

        for (Question q:questionList) {
            if(q.getQuestionId() == questionId){
                return q;
            }
        }
        return new Question(-1);
    }

    public void addEnteredAnswer(EnteredAnswer enteredAnswer){
        Question question = getQuestionId(enteredAnswer.getQuestionId());
        int indexOfQuestion = questionList.indexOf(question);
    //     if(question.getQuestionId() > 0){
             questionList.get(indexOfQuestion).addEnteredAnswer(enteredAnswer);
      //   }else {
             //Error
       //  }
    }

    public List<Question> getQuestionList(){
        return questionList;
    }


}

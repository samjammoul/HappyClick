package CommunicatorClient;

import CommunicatorClient.CommunicatorClientWebSocket;


public class Main {

    /*

            requires javafx.controls;
    requires java.sql;
    requires javafx.graphics;
    requires javax.websocket.client.api;
    requires javafx.fxml;
    requires gson;
 requires Communicatorshared;
    requires org.slf4j;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;


    opens Ui;
      exports Ui;


     */



    private static Communicator communicator;

    //    private CommunicatorClient.Communicator communicator = null;
    public static void main(String[] args) throws Exception {
        communicator = CommunicatorClientWebSocket.getInstance();

        // Establish connection with server
        communicator.start();
    //    communicator.newGame(1, "fa");
       /// SeaBattleRestClient rest = new SeaBattleRestClient();
  //   System.out.println(rest.newPlayer("sam", "Rest"));
      //  System.out.println( "Rest");
    }

    public void print(String playername, String password) {

        System.out.println(playername + "  ||  " + password);

    }

}




module Client {

    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires javax.websocket.client.api;
    requires gson;
    requires Communicatorshared;
 //   requires org.slf4j;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires java.sql;

    exports CommunicatorClient to org.eclipse.jetty.websocket.common;
    opens Ui;
}
package share;

public class CommunicatorWebSocketMessage {

    // Operation
    private CommunicatorWebSocketMessageOperation operation;



    // Content
    private String data;

    public CommunicatorWebSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(CommunicatorWebSocketMessageOperation operation) {
        this.operation = operation;
    }



    public String getData() {
        return data;
    }

    public void setData(String content) {
        this.data = content;
    }
}

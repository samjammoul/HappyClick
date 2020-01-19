package share;

public class CommunicatorWebSocketMessage {

    // Operation that is requested at client side
    private CommunicatorWebSocketMessageOperation operation;

    // Property
  //  private String property;

    // Content
    private String data;

    public CommunicatorWebSocketMessageOperation getOperation() {
        return operation;
    }

    public void setOperation(CommunicatorWebSocketMessageOperation operation) {
        this.operation = operation;
    }

    //public CommunicatorWebSocketMessageOperation getProperty() {
    //    return property;
 //   }

  //  public void setProperty(CommunicatorWebSocketMessageOperation property) {
  //      this.property = property;
//    }

    public String getData() {
        return data;
    }

    public void setData(String content) {
        this.data = content;
    }
}

package Rest;

import com.google.gson.Gson;

public class RestMessage {

    private RestStatus status;
    private String message;
    private String data;

    public RestMessage(RestStatus status, String message, String data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public RestStatus getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return message;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }
}

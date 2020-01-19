package HappyClickGame;

import share.RequestData;

public interface IRequestUiController {

    void getRequest(RequestData requestData);

    void acceptRequest();

    void rejectRequest();

    RequestData getRequestData();

     int getRequestId();

     int getGameIdOfRequest();

    void closeUi();
}

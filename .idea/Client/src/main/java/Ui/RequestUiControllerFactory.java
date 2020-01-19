package Ui;

import HappyClickGame.IRequestUiControllerFactory;
import HappyClickGame.Lobby;

public class RequestUiControllerFactory implements IRequestUiControllerFactory {
    private static  RequestUiController requestUiControllerUi ;
   // private RequestUiController requestUiController;




    @Override
    public RequestUiController getRequestUiController(Lobby lobby ) {
        return   requestUiControllerUi = new RequestUiController(lobby);
    }

    @Override
    public RequestUiController getRequestUiControllerForUi() {
        return requestUiControllerUi;
    }
}

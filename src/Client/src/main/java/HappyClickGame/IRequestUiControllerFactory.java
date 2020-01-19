package HappyClickGame;

import Ui.RequestUiController;

public interface IRequestUiControllerFactory
{
    RequestUiController getRequestUiController(Lobby lobby );

    RequestUiController getRequestUiControllerForUi();
}

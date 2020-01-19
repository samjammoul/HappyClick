package Ui;

import HappyClickGame.User;

import java.util.List;

public interface IInloggenController {

    void loginSucceed();

    void loginfailed();

    void addOnlineUsers(List<User> users);
}

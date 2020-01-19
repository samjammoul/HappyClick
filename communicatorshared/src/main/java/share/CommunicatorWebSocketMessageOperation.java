package share;

public enum CommunicatorWebSocketMessageOperation {
    RegisterProperty, RegisterSuccess, RegisterNotSuccess, RegisterFailed,   // Registration
    logInProperty, loginSuccess, logInNotSuccess,                           // login
    CreatGame, CreatGameNotSuccess, CreatGameSuccess,                      // Create Game
    OnlineUsers,                                                          // list of online users
    SendRequest, SendRequestNotSuccess, GetRequest, RequestResponse,     // send request
    RequestRejected, RequestAccepted, RequestIsSent, RequestCancel,     // Request status
    GetQuestion, EnteredAnswer, GetResult,                             // Messages during the game
    StartGame,                                                        //  begin the game
    GameIsFull,                                                      //   when the players number is 4 in the game
    GetGameInformation,                                             //   when the user accept the request , server send the information of the game
}

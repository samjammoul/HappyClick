package share;

public enum CommunicatorWebSocketMessageOperation {
    REGISTERPROPERTY,         // Register a property (User only)
    RegisterSuccess,
    RegisterNotSuccess,
    RegisterFailed,
    logInProperty,
    loginSuccess,
    logInNotSuccess,
    CreatGame,
    CreatGameNotSucess,
    CreatGameSucess,
    OnlineUsers,
    SendRequest,
    SendRequestNotSucess,
    GetRequest,
    RequestResponse,
    RequestRejected,
    RequestAccepted,
    RequestIsSent,
    GameIsFull,
    RequestCancel,
    GetQuestion,
    EnteredAnswer,
    GetResult,
    StartGame,
    GetGameInformation,
}

package share;

public enum CommunicatorWebSocketMessageOperation {
    REGISTERPROPERTY,         // Register a property (client only)
    UNREGISTERPROPERTY,       // Unregister a registered property (client only
    SUBSCRIBETOPROPERTY,      // Subscribe to a property (client only)
    UNSUBSCRIBEFROMPROPERTY,  // Unsubscribe from a property (client only)
    UPDATEPROPERTY,
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

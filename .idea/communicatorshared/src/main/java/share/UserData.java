package share;

public class UserData {
    private int userId;
    private String password;
    private String name;



    public UserData(int userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public UserData(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
    public UserData(int userId ) {
        this.userId = userId;

    }

    public int getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }
}

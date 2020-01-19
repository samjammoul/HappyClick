package HappyClickGame;

import share.RequestData;
import share.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int userNumber;

    private String name;


  private   List<RequestData> requestDatalist = new ArrayList<>();

    public User(int userNumber, String name) {
        this.userNumber = userNumber;
        this.name = name;

    }


    public int getUserNumber() {
        return this.userNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        return name ;
    }


    public List<RequestData> getRequestDatalist() {
        return requestDatalist;
    }

    public void setRequestDatalist(List<RequestData> requestDatalist) {
        this.requestDatalist = requestDatalist;
    }

    public void  addRequest(RequestData requestData){
        requestDatalist.add(requestData);
    }
}

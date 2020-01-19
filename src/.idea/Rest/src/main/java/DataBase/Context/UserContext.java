package DataBase.Context;

import DataBase.ConToDb;
import DataBase.Repo.UserRepo;
import Models.User;
import share.UserData;
import share.UserStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserContext implements IUserContext {

    ConToDb conToDb = new ConToDb();
    UserRepo userRepo = new UserRepo();

    // i have to test the methode and link it to the Client and ui


    public  int  RegisterUser(String userName , String userPassword ) {
        if(checkUsername(userName) == 0 ) {
        try {

                conToDb.loadDBFile();
                Statement statement = conToDb.conncteToDb().createStatement();


                PreparedStatement myStat = conToDb.conncteToDb().prepareStatement("insert INTO  user   (Name,Password) VALUES (?,?)");

                myStat.setString(1, userName);
                myStat.setString(2, userPassword);

                myStat.executeUpdate();

             //   conToDb.conncteToDb().close();
            conToDb.closeConnection();
                try {
                    if (conToDb.conncteToDb() != null) {
                        conToDb.conncteToDb().close();
                    }


                } catch (SQLException e) {
                    e.printStackTrace();

                }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            setUserStatus(getUserId(userName),UserStatus.Online);
        }catch (Exception ex){

        }
        return getUserId(userName);
        }
        return 0 ;

    }

    public int checkUsername(String EnteredName){
        List<UserData> users = new ArrayList<>();
        try{
            conToDb.loadDBFile();
            Statement statement =  conToDb.conncteToDb().createStatement();

            String query = "SELECT * FROM dbi401070.user";
            Statement st = conToDb.conncteToDb().createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String password = rs.getString("Password");


                UserData  userData = new UserData(id,name,password);
                users.add(userData);

            }


               // conToDb.conncteToDb().close();

            conToDb.closeConnection();


        }
        catch (Exception e)
        {
            System.err.println("Got an exception! | checkUsername  ");
            System.err.println(e.getMessage());
        }
        for (UserData user :users) {
            if(user.getName().equals(EnteredName)){

                return user.getUserId();
            }


        }
        return 0;
    }

    public int getUserId(String EnteredName){
        int id = 0;
        try{
            conToDb.loadDBFile();
            Statement statement =  conToDb.conncteToDb().createStatement();


            PreparedStatement myStat = conToDb.conncteToDb().prepareStatement("SELECT user.Id from user where Name =?");

            myStat.setString(1, EnteredName);



            // execute the query, and get a java resultset
            ResultSet rs = myStat.executeQuery();

            // iterate through the java resultset
            while (rs.next())
            {
                 id = rs.getInt("Id");


            }


          //  conToDb.conncteToDb().close();
            conToDb.closeConnection();

        }
        catch (Exception e)
        {
            System.err.println("Got an exception! | getUserId  ");
            System.err.println(e.getMessage());
        }

        return id;
    }

    public List <User> getallUsers( ){
        List<User> users = new ArrayList<>();
        try{
            conToDb.loadDBFile();
            Statement statement =  conToDb.conncteToDb().createStatement();

            String query = "SELECT * FROM dbi401070.user";
            Statement st = conToDb.conncteToDb().createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String password = rs.getString("Password");


                User  user = new User(id,name,password);
                users.add(user);

            }


     //       conToDb.conncteToDb().close();

            conToDb.closeConnection();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! | checkUsername  ");
            System.err.println(e.getMessage());
        }

        return users;
    }

    public  void  setUserStatus(int userId , UserStatus userStatus ) {

            try {

                conToDb.loadDBFile();
                Statement statement = conToDb.conncteToDb().createStatement();


                PreparedStatement myStat = conToDb.conncteToDb().prepareStatement("UPDATE  user set   Status = ? where Id = ? ");

                myStat.setString(1, userStatus.toString());
                myStat.setInt(2, userId);

                myStat.executeUpdate();

           //     conToDb.conncteToDb().close();
                conToDb.closeConnection();

                try {
                    if (conToDb.conncteToDb() != null) {
                        conToDb.conncteToDb().close();
                    }


                } catch (SQLException e) {
                    e.printStackTrace();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }




    }


    public void getallOnlineUsers( ){
        List<User> onlineusers = new ArrayList<>();
        try{
            conToDb.loadDBFile();
            Statement statement =  conToDb.conncteToDb().createStatement();

            String query = "SELECT * FROM dbi401070.user where Status = 'Online'";
            Statement st = conToDb.conncteToDb().createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                String password = rs.getString("Password");
                String status = rs.getString("Status");

                User  user = new User(id,name,password,UserStatus.Online);

                userRepo.addUser(user);

            }


      //      conToDb.conncteToDb().close();
            conToDb.closeConnection();


        }
        catch (Exception e)
        {
            System.err.println("Got an exception! | getallOnlineUsers  ");
            System.err.println(e.getMessage());
        }


    }
}

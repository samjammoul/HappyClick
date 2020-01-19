package Server;

import DataBase.Context.UserContext;
import DataBase.Repo.UserRepo;
import Models.User;
import Models.RestMessage;
import Models.RestStatus;
import com.google.gson.Gson;
import org.slf4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/AccountsHandler")
 public class AccountsHandlerRESTService {
    private static final Logger log = RestServer.getLogger();
    private static final Gson gson = new Gson();
    private UserRepo userRepo = new UserRepo(new UserContext());

    @POST
    @Consumes("application/json")
    @Path("/register")
    public Response addPlayer(String json) {
        log.info("[Server addPlayer]");

        try {
            User user = gson.fromJson(json, User.class);
            user.setPlayerNumber(userRepo.RegisterUser(user.getName(),user.getPassword()));
            RestMessage message = new RestMessage(RestStatus.SUCCESS, "Player " + user.getName() + " added", gson.toJson(user));
            return Response.status(201).entity(message.toJson()).build();
        } catch (Exception e) {
            RestMessage message = new RestMessage(RestStatus.ERROR, e.getLocalizedMessage(), json);
            return Response.status(400).entity(message.toJson()).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public Response login(String json) {
        log.info("[Server addPlayer]");

        try {
            // getallUsers
            User user = gson.fromJson(json, User.class);
            List<User> users = new ArrayList<>();
            users = userRepo.getallUsers();
            for (User u :users) {

                if (u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())){
                    user.setPlayerNumber(u.getUserNumber());
                    RestMessage message = new RestMessage(RestStatus.SUCCESS, "Player " + user.getName() + " added", gson.toJson(user));
                    return Response.status(201).entity(message.toJson()).build();
                }

            }
            RestMessage message = new RestMessage(RestStatus.ERROR, "No user information founded", json);
            return Response.status(400).entity(message.toJson()).build();
           // player.setPlayerNumber(userRepo.RegisterUser(player.getName(),player.getPassword()));

        } catch (Exception e) {
            RestMessage message = new RestMessage(RestStatus.ERROR, e.getLocalizedMessage(), json);
            return Response.status(400).entity(message.toJson()).build();
        }
    }
}

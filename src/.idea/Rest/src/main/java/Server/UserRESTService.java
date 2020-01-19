package Server;


import DataBase.Context.UserContext;
import DataBase.Repo.UserRepo;
import Models.User;
import Models.RestMessage;
import Models.RestStatus;
import Models.User;
import com.google.gson.Gson;
import org.slf4j.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/users")
public class UserRESTService {
    private static final Logger log = RestServer.getLogger();
    private static final Gson gson = new Gson();
    private UserRepo userRepo = new UserRepo(new UserContext());

    @POST
    @Consumes("application/json")
    @Path("/online")
    public Response showAllOnlineUsers() {
        log.info("[Server showOnlineUsers]");

        try {
            userRepo.getallOnlineUsers();
            List<User> users = userRepo.getOnlineUsers();
            RestMessage message = new RestMessage(RestStatus.SUCCESS, "Got " + users.size() + "online users.", gson.toJson(users));
            return Response.status(200).entity(message.toJson()).build();
        } catch (Exception e) {
            RestMessage message = new RestMessage(RestStatus.ERROR, e.getLocalizedMessage(), null);
            System.out.println("+++++++++++++++++++++++++++++++++++++++");
            System.out.println(e);
            return Response.status(400).entity(message.toJson()).build();
        }
    }
}

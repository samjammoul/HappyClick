package rest;

import Server.User;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HappyClickRestClient {
    private static final Gson gson = new Gson();
    private static final String url = "http://localhost:7903";
    private static final Logger logger = LoggerFactory.getLogger(HappyClickRestClient.class.getCanonicalName());


    public int newUser(String name, String password) throws Exception {
        User newPlayer = new User(name, password);
        String target = "/AccountsHandler/register";
        RestMessage response = executeQueryPost(newPlayer, target);

        if (response.getStatus() == RestStatus.ERROR) {

            throw new Exception(response.getStatusMessage());
        }

        User insertedPlayer = gson.fromJson(response.getData(), User.class);
        return insertedPlayer.getUserNumber();

    }
    public User login(String name, String password) throws Exception {
        User user = new User(name, password);
        String target = "/AccountsHandler/login";
        RestMessage response = executeQueryPost(user, target);

        if (response.getStatus() == RestStatus.ERROR) {

            throw new Exception(response.getStatusMessage());
        }

        User insertedUser = gson.fromJson(response.getData(), User.class);
        return insertedUser;

    }

    public List<User> getAllOnlineUsers() throws Exception {
        String target = "/users/online";
        RestMessage response = executeQueryPost2(target);

        if (response.getStatus() == RestStatus.ERROR) {

            throw new Exception(response.getStatusMessage());
        }
        Type listType = new TypeToken<List<User>>(){}.getType();
        List<User> onlineUsers = gson.fromJson(response.getData(),  listType);
        return onlineUsers;

    }
    private RestMessage executeQueryPost(User seaBattleRequest, String queryPost) {
        final String query = url + queryPost;

        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;
        try {
            params = new StringEntity(gson.toJson(seaBattleRequest));
            httpPost.setEntity(params);
        } catch (UnsupportedEncodingException ex) {
            return new RestMessage(RestStatus.ERROR, ex.getLocalizedMessage(), null);
        }
        return executeHttpUriRequest(httpPost);
    }

    private RestMessage executeQueryPost2( String queryPost) {
        final String query = url + queryPost;

        HttpPost httpPost = new HttpPost(query);
        httpPost.addHeader("content-type", "application/json");
        StringEntity params;

        return executeHttpUriRequest(httpPost);
    }

    private RestMessage executeHttpUriRequest(HttpUriRequest httpUriRequest) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            CloseableHttpResponse response = httpClient.execute(httpUriRequest);
            HttpEntity entity = response.getEntity();
            String entityString = EntityUtils.toString(entity);
            return gson.fromJson(entityString, RestMessage.class);
        } catch (IOException | JsonSyntaxException e) {
            return new RestMessage(RestStatus.ERROR, e.getLocalizedMessage(), null);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                return new RestMessage(RestStatus.ERROR, e.getLocalizedMessage(), null);
            }
        }
    }
}

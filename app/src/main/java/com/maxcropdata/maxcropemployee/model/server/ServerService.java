package com.maxcropdata.maxcropemployee.model.server;

import com.maxcropdata.maxcropemployee.model.server.request.ServerRequest;
import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerService  implements JSONAble<Server> {

    private static ServerService instance = new ServerService();
    public static ServerService getInstance() {
        return instance;
    }

    public static Server getDefaultServer() {


        final Server server = new Server.Builder()
                .address("maxcropdata.com")
                .webserviceAddress("api/mcm")
                .database("maxcrop_prod")
                .protocol("https")
                .build();

         return server;
    }

    @Override
    public String toJSON(Server s) throws IllegalAccessException {
        return JSONService.formatAsJSON(s);
    }

    @Override
    public Server fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        Server server = new Server();

        JSONService.readJSONIntoObject(json, server);

        return server;
    }

    public void executeServerRequest(ServerRequest serverRequest) {
        /*
        TODO: limit server requests - temporary to 5 daily?
         */
        serverRequest.execute();
    }

}

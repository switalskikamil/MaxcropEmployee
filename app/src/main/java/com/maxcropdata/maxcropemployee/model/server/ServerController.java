package com.maxcropdata.maxcropemployee.model.server;

import android.content.Context;

import com.maxcropdata.maxcropemployee.model.server.request.ServerRequest;
import com.maxcropdata.maxcropemployee.shared.utils.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerController {
    static final String FILE_NAME = "server_config.json";

    private static ServerController instance = new ServerController();

    public static ServerController getInstance() {
        return instance;
    }

    /**
     * @param context Application context
     * @param server Server object for storing
     * @throws IllegalAccessException
     */
    public static void saveServerToFileSystem(
            Context context,
            Server server)
            throws IllegalAccessException {
        saveServerToFileSystem(context, server, FILE_NAME);
    }


    public static void saveServerToFileSystem(
            Context context,
            Server server,
            String fileName)
            throws IllegalAccessException {
        ServerService service = new ServerService();
        FileManager.writeFileToStorage(context, fileName, service.toJSON(server));
    }


    public static Server readServerFromFileSystem(Context context)
            throws JSONException, IllegalAccessException {
        return readServerFromFileSystem(context, FILE_NAME);
    }

    public static Server readServerFromFileSystem(
            Context context,
            String fileName
    ) throws JSONException, IllegalAccessException {

        String file = FileManager.readFileFromStorage(context, fileName);

        if (file != null) {
            return ServerService.getInstance().fromJSON(new JSONObject(file));
        } else {
            return ServerService.getDefaultServer();
        }
    }

    public void executeServerRequest(ServerRequest serverRequest) {
        ServerService.getInstance().executeServerRequest(serverRequest);
    }

}

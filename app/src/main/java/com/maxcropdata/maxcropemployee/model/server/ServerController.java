package com.maxcropdata.maxcropemployee.model.server;

import android.content.Context;

import com.maxcropdata.maxcropemployee.shared.utils.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

public class ServerController {
    public static final String FILE_NAME = "server_config.json";


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

        ServerService service = new ServerService();

        String file = FileManager.readFileFromStorage(context, fileName);

        if (file != null) {
            return service.fromJSON(new JSONObject(file));
        } else {
            return ServerService.getDefaultServer();
        }
    }

}

package com.maxcropdata.maxcropemployee.model.server;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServerServiceTest {

    ServerService underTests = new ServerService();

    @Test
    public void isProducingDefaultServer() {
        // check if default server is produced
        assertNotNull(ServerService.getDefaultServer());
    }

    @Test
    public void isProducingProperJSONFile() throws IllegalAccessException {
        // given
        Server server = ServerService.getDefaultServer();

        // when
        String jsonFile = underTests.toJSON(server);

        // then
        try {
            new JSONObject(jsonFile);
        } catch (JSONException e) {
            fail(e.toString());
        }
    }

    @Test
    public void isProperlyReadingFromJSON()  {

        try {
            // given
            Server expectedServer = ServerService.getDefaultServer();
            JSONObject serverJSON = new JSONObject(
                    underTests.toJSON(
                            expectedServer
                    )
            );


            // when
            Server actualServer = underTests.fromJSON(serverJSON);

            // then
            assertEquals(expectedServer, actualServer);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


}
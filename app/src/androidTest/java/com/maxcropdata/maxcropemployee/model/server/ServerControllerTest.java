package com.maxcropdata.maxcropemployee.model.server;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;


public class ServerControllerTest {

    private static String testFileName = "test_" + ServerController.FILE_NAME;

    @Test
    public void canSaveServerToFileSystem() {
        // given
        Context context = ApplicationProvider.getApplicationContext();

        // then
        try {
            ServerController.saveServerToFileSystem(context, ServerService.getDefaultServer(), testFileName);
        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void readServerFromFileSystem() {
        // given
        Context context = ApplicationProvider.getApplicationContext();

        Server expectedServer = new Server.Builder()
                .address("test.maxcrop.com")
                .database("maxcrop_test")
                .protocol("http")
                .webserviceAddress("api/test")
                .build();


        try {
            // when
            ServerController.saveServerToFileSystem(context, expectedServer, testFileName);

            Server actualServer = ServerController.readServerFromFileSystem(context, testFileName);


            // then
            assertEquals(expectedServer, actualServer);

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }


}
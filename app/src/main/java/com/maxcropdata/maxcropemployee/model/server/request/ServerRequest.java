package com.maxcropdata.maxcropemployee.model.server.request;

import android.net.Uri;
import android.os.AsyncTask;

import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.model.token.TokenService;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public abstract class ServerRequest extends AsyncTask<Void, Void, String> {

    public enum MethodType {
        POST, GET
    }

    private Token authToken;
    private String payload;  //can be registration form, report data request or issue report
    private Server server;
    private AsyncResponseProcessor delegatedProcessor;
    private MethodType method;
    private String endPoint;

    private HttpURLConnection connection;
    private int httpResponseCode;

    ServerRequest(Token authToken, String payload, Server server, AsyncResponseProcessor delegatedProcessor, MethodType method, String endPoint) {
        this.authToken = authToken;
        this.payload = payload;
        this.server = server;
        this.delegatedProcessor = delegatedProcessor;
        this.method = method;
        this.endPoint = endPoint;
    }

    protected void onPreExecute() {
        try {
            // prepare http connection
            prepareConnection();

            // write and encode query
            prepareQuery();

        } catch (IOException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Call after onPreExecute method
    protected String doInBackground(Void... params) {
        String result;
        BufferedReader reader ;
        InputStream inputStream;

        try {
            getConnection().connect();

            this.httpResponseCode = getConnection().getResponseCode();

            if (this.httpResponseCode < HttpURLConnection.HTTP_BAD_REQUEST) {
                inputStream = getConnection().getInputStream();
            } else {
                inputStream = getConnection().getErrorStream();
            }
            StringBuffer buffer = new StringBuffer();

            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) buffer.append(line);

                if (buffer.length() > 0) {
                    result = buffer.toString();
                    return result;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private void prepareConnection() throws IOException {
        URL url = new URL(this.server.toString() + this.endPoint);

        connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(10000);
        connection.setRequestMethod(this.method.name());

        connection.setDoOutput(true);
        connection.setDoInput(true);
    }

    private void prepareQuery() throws IllegalAccessException, IOException {
        Uri.Builder builder = new Uri.Builder()
                .appendQueryParameter("auth", TokenService.getInstance().toJSON(this.authToken))
                .appendQueryParameter("payload", this.payload)
                .appendQueryParameter("db", this.server.getDatabase());

        String query = builder.build().getEncodedQuery();

        OutputStream os = connection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, StandardCharsets.UTF_8));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();
    }


    AsyncResponseProcessor getDelegatedProcessor() {
        return delegatedProcessor;
    }

    private HttpURLConnection getConnection() {
        return connection;
    }

    int getHttpResponseCode() {
        return httpResponseCode;
    }


}

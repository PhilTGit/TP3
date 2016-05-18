package com.sensors.philippe.sensorstest.Modele;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class RequestDatabaseTask extends AsyncTask<Object, Integer, Object> {

    private final String IP_ADRESS = "10.200.76.175:8080";
    private final String DATABASE_NAME = "database";

    private DatabaseManagerListener listener;
    private RequestType requestType;
    private Object[] extras;

    public RequestDatabaseTask() {

    }

    @Override
    protected Object doInBackground(Object... params) {
        if (params.length >= 2) {
            this.setParamsFromParams(params);

            String link = "http://" + IP_ADRESS + "/" + DATABASE_NAME + "/";
            HttpURLConnection connection = null;
            try {
                switch (requestType) {
                case CREATE_ACCOUNT:
                    link += "accounts/";
                    connection = this.getConnection(link, "PUT");
                    if (connection != null) {
                        connection.setRequestProperty("Accept", "application/json");
                        connection.setDoOutput(true);

                        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                        out.write((String)extras[0]);
                        //out.flush();
                        out.close();

                        int responseCode = connection.getResponseCode();

                        connection.disconnect();

                        if (responseCode == 500) {
                            //TODO Exception.
                        }
                    }
                    return (String)extras[0];
                    case GET_ACCOUNT:
                        link += "accounts/" + (String)extras[0];
                        connection = this.getConnection(link, "GET");
                        if (connection != null) {
                            connection.connect();

                            ObjectMapper mapper = new ObjectMapper();
                            Account acc = mapper.readValue(connection.getInputStream(), Account.class);

                            connection.disconnect();

                            if (acc != null) {
                                return acc;
                            } else {
                                //TODO Exception.
                            }
                        }
                default:
                    //TODO Exception.
            }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        DatabaseManager.requestResult(this.listener, this.requestType, o);
    }

    private void setParamsFromParams(Object[] params) {
        //Il y a toujours le listener à l'indice 0 et le 'RequestType' à l'indice 1.
        listener = (DatabaseManagerListener)params[0];
        requestType = (RequestType)params[1];
        //Il y a un tableau d'object à l'indice '2' qui contient les paramètres supplémentaires.
        extras = (Object[])params[2];
    }

    private HttpURLConnection getConnection(String link, String requestMethod) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("PUT");
            return connection;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

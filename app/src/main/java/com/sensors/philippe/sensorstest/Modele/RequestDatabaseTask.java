package com.sensors.philippe.sensorstest.Modele;

import android.os.AsyncTask;


public class RequestDatabaseTask extends AsyncTask<Object, Integer, Object> {

    private CallBack callBack;
    private DatabaseManagerListener listener;
    private RequestType requestType;

    public RequestDatabaseTask(CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected Object doInBackground(Object... params) {
        if (params.length >= 2) {
            //Il y a toujours le listener à l'indice 0 et le 'RequestType' à l'indice 1.
            listener = (DatabaseManagerListener)params[0];
            requestType = (RequestType)params[1];

            switch (requestType) {
                case CREATE_ACCOUNT:
                    //TODO Effectuer la requête dans la base de données.
                    return null;
                default:
                    //TODO Exception.
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        this.callBack.requestResult(this.listener, this.requestType, o);
    }

    public interface CallBack {
        void requestResult(DatabaseManagerListener listener, RequestType requestType, Object object);
    }
}

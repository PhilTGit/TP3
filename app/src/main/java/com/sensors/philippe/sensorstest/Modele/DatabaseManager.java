package com.sensors.philippe.sensorstest.Modele;

public class DatabaseManager {

    public DatabaseManager() {
    }

    public static void requestDatabase(DatabaseManagerListener listener, RequestType requestType, Object... params) {
        RequestDatabaseTask task = new RequestDatabaseTask();
        switch (requestType) {
            case CREATE_ACCOUNT:
                task.execute(listener, requestType, params);
                break;
            case GET_ACCOUNT:
                task.execute(listener, requestType, params);
            default:
                //TODO exception.
        }
    }

    public static void requestResult(DatabaseManagerListener listener, RequestType requestType, Object object) {
        listener.requestResult(requestType, object);
    }
}

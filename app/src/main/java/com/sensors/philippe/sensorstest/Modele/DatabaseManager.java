package com.sensors.philippe.sensorstest.Modele;

public class DatabaseManager {

    public DatabaseManager() {
    }

    public static void requestDatabase(DatabaseManagerListener listener, RequestType requestType) {
        switch (requestType) {
            case CREATE_ACCOUNT:
                RequestDatabaseTask task = new RequestDatabaseTask();
                task.execute(listener, requestType);
                break;
            default:
                //TODO exception.
        }
    }

    public static void requestResult(DatabaseManagerListener listener, RequestType requestType, Object object) {
        listener.requestResult(requestType, object);
    }
}

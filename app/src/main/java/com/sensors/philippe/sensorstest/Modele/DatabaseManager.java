package com.sensors.philippe.sensorstest.Modele;

public class DatabaseManager implements RequestDatabaseTask.CallBack{

    public DatabaseManager() {
    }

    public void requestDatabase(DatabaseManagerListener listener, RequestType requestType) {
        switch (requestType) {
            case CREATE_ACCOUNT:
                RequestDatabaseTask task = new RequestDatabaseTask(this);
                task.execute(listener, requestType);
                break;
            default:
                //TODO exception.
        }
    }

    @Override
    public void requestResult(DatabaseManagerListener listener, RequestType requestType, Object object) {
        listener.requestResult(requestType, object);
    }
}

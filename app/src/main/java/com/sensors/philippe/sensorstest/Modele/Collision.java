package com.sensors.philippe.sensorstest.Modele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sensors.philippe.sensorstest.R;

import java.util.Date;

public class Collision {

    private java.sql.Date collisionDate;
    private double collisionStrength;
    private String collisionOwner;
    private boolean callDone;

    public Collision(java.sql.Date collisionDate, double collisionStrength, String collisionOwner, boolean callDone) {

        this.collisionDate = collisionDate;
        this.collisionStrength = collisionStrength;
        this.collisionOwner = collisionOwner;
        this.callDone = callDone;
    }

    public Date getcollisionDate() {
        return collisionDate;
    }

    public void setcollisionDate(java.sql.Date collisionDate) {
        this.collisionDate = collisionDate;
    }

    public double getcollisionStrength() {
        return collisionStrength;
    }

    @Override
    public String toString() {
        String toReturn = String.valueOf(R.string.collisionOwner) + collisionOwner
                +String.valueOf(R.string.collisionDate) + collisionDate +
                String.valueOf(R.string.collisionStrength) + collisionStrength +
                String.valueOf(R.string.callDone);

        if(callDone){
            toReturn+= String.valueOf(R.string.yes) +"/n";
        }
        else{
            toReturn+= String.valueOf(R.string.no)+"/n";
        }

        return toReturn;


    }

    public void setcollisionStrength(double collisionStrength) {
        this.collisionStrength = collisionStrength;
    }

    public String getcollisionOwner() {
        return collisionOwner;
    }

    public void setcollisionOwner(String collisionOwner) {
        this.collisionOwner = collisionOwner;
    }

    public boolean isCallDone() {
        return callDone;
    }

    public void setCallDone(boolean callDone) {
        this.callDone = callDone;
    }


}

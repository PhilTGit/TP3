package com.sensors.philippe.sensorstest.Modele;

import java.util.Date;

public class Colision {

    private java.util.Date colisionDate;
    private double colisionStrength;
    private String colisionOwner;
    private boolean callDone;

    public Colision(Date colisionDate, double colisionStrength, String colisionOwner, boolean callDone) {

        this.colisionDate = colisionDate;
        this.colisionStrength = colisionStrength;
        this.colisionOwner = colisionOwner;
        this.callDone = callDone;
    }

    public Date getColisionDate() {
        return colisionDate;
    }

    public void setColisionDate(Date colisionDate) {
        this.colisionDate = colisionDate;
    }

    public double getColisionStrength() {
        return colisionStrength;
    }

    public void setColisionStrength(double colisionStrength) {
        this.colisionStrength = colisionStrength;
    }

    public String getColisionOwner() {
        return colisionOwner;
    }

    public void setColisionOwner(String colisionOwner) {
        this.colisionOwner = colisionOwner;
    }

    public boolean isCallDone() {
        return callDone;
    }

    public void setCallDone(boolean callDone) {
        this.callDone = callDone;
    }


}

package com.sensors.philippe.sensorstest.Modele;

/**
 * Created by philippe on 2016-05-02.
 */
public class Account {
    /**
     * Poids en ______ du proriétaire de l'accompte.
     * */
    private float weight;
    /**
     * Nom du propriétaire.
     * */
    private String name;
    /**
     * Prénom du propriétaire.
     * */
    private String firstName;
    /**
     * Pseudonyme de l'accompte.
     * */
    private String accountID;

    public Account() {
    }

    public Account(float weight, String name, String firstName, String accountID) {
        this.weight = weight;
        this.name = name;
        this.firstName = firstName;
        this.accountID = accountID;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAccountID() {
        return accountID;
    }
}

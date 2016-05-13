package com.sensors.philippe.sensorstest.Modele;

public class Account {
    /**
     * Poids en kg du proriétaire de l'accompte.
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
     * Pseudonyme du compte.
     * */
    private String accountID;
    /**
     * Le numéro d'urgence à appeler en cas d'accident majeur.
     * */
    private int emergencyNumber;

    /**
     * Valeur booléenne indiquant si l'utilisateur porte toujour sa ceinture de sécurité.
     */
    private boolean seatBeltAlwaysOn;

    public Account() {
    }

    public Account(String accountID, String name, String firstName, int emergencyNumber, float weight, boolean seatBeltAlwaysOn) {
        this.weight = weight;
        this.name = name;
        this.firstName = firstName;
        this.accountID = accountID;
        this.emergencyNumber = emergencyNumber;
        this.seatBeltAlwaysOn = seatBeltAlwaysOn;
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

    public int getEmergencyNumber() {
        return emergencyNumber;
    }

    public boolean isSeatBeltAlwaysOn() {
        return seatBeltAlwaysOn;
    }
}

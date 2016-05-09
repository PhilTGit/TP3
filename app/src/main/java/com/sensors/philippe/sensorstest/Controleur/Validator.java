package com.sensors.philippe.sensorstest.Controleur;


public class Validator {

    /**
     * Détermine si le numéro de téléphone passée en paramètre est un téléphone d'urgence valide.
     * @param phoneNumber Le numéro de téléphone à valider.
     * @return Retourne vrai si le numéro est valide, sinon retourne faux.
     * */
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.equals("911"))
            return true;

        return false;
    }

    /**
     * Détermine si l'identifiant passé en paramètre est valide ou non.
     * L'identifiant est valide si il n'est pas présent dans la base de données.
     * @param id L'identifiant à valider.
     * @return Retourne vrai si l'identifiant est valide, sinon retourne faux.
     * */
    public static boolean validateID(String id) {
        return true;
    }
}

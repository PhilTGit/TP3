package com.sensors.philippe.sensorstest.Modele;

import android.hardware.SensorEvent;

import java.math.BigDecimal;

public class ForcesCalculator {

    /**
     * Calcule la force en Newton(N) de l'impact subit par le corps lors de changement d'accélération
     * @param userWeight Le poids de l'utilisateur vu que la formule de la force selon la loi de NEwton est f=m*a
     * @param event l'événement contenant les données de l'accéléromêtre.
     * @param seatBeltAlwaysOn Un booléen savoir si l'utilisateur porte toujours sa ceinture de sécurité pour savoir si
     *                         une partie de la force est dissipée.
     * @return La force en Newton subit par l'utilisateur
     */
    public static double calculateNforceOnBody (float userWeight, SensorEvent event, boolean seatBeltAlwaysOn){
        double forceOnBody = 0.0d;
        double totalForcesOnBody = event.values[0] + event.values[1] + event.values[2];

        if ((totalForcesOnBody - 9.8)< -1){
            return -1;
        }
        forceOnBody = userWeight * (totalForcesOnBody - 9.8);
        if(seatBeltAlwaysOn){
            forceOnBody = forceOnBody *0.5;
        }
        return forceOnBody;
    }
    public static double claculateHadInjuryCriterion(SensorEvent event){
        double hic=0.0d;

        double totalForcesOnBodyMinusGravity = event.values[0] + event.values[1] + event.values[2]-9.8;

        double t1 = 0.0d;
        double t2 = 0.15d;

        BigDecimal t = new BigDecimal(5);

        //TODO change double for BigDecimal

        //hic = (t2-t1)*(((1/(t2-t1))*totalForcesOnBodyMinusGravity)^2.5);

        return hic;
    }


}

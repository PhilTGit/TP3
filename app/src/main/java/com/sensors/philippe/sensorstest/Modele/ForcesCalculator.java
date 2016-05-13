package com.sensors.philippe.sensorstest.Modele;

import android.hardware.SensorEvent;

import java.math.BigDecimal;

public class ForcesCalculator {

    /**
     * Calcule la force en Newton(N) de l'impact subit par le corps lors de changement d'accélération
     * @param userWeight Le poids de l'utilisateur vu que la formule de la force selon la loi de NEwton est f=m*a
     * @param seatBeltAlwaysOn Un booléen savoir si l'utilisateur porte toujours sa ceinture de sécurité pour savoir si
     *                         une partie de la force est dissipée.
     * @return La force en Newton subit par l'utilisateur
     */
    public static double calculateNforceOnBody (float userWeight, String[] valueOfAccelerometer, boolean seatBeltAlwaysOn){
        double forceOnBody = 0.0d;
        double totalForcesOnBody = Integer.valueOf(valueOfAccelerometer[0]) +
                                   Integer.valueOf(valueOfAccelerometer[1]) +
                                   Integer.valueOf(valueOfAccelerometer[2])-9.8;


        if ((totalForcesOnBody - 9.8)< -1){
            return -1;
        }
        forceOnBody = userWeight * (totalForcesOnBody - 9.8);
        if(seatBeltAlwaysOn){
            forceOnBody = forceOnBody *0.5;
        }
        return forceOnBody;
    }
    public static double calculateHadInjuryCriterion(String[] valueOfAccelerometer){

        BigDecimal totalForcesOnBodyMinusGravity = new BigDecimal( Integer.valueOf(valueOfAccelerometer[0]) +
                Integer.valueOf(valueOfAccelerometer[1]) +
                Integer.valueOf(valueOfAccelerometer[2])-9.8);

        totalForcesOnBodyMinusGravity = totalForcesOnBodyMinusGravity.pow(5);

       BigDecimal hic = BigDecimal.valueOf((0.15)*(((1/(0.15))* Math.sqrt(totalForcesOnBodyMinusGravity.doubleValue()))));

        return hic.longValueExact();
    }


}

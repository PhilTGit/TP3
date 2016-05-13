package com.sensors.philippe.sensorstest.Modele;

import android.hardware.SensorEvent;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.Assert.*;

public class ForcesCalculatorTest {




    @Test
    public void canDetectBrokenAccellerometer() throws Exception {
        String[] values = new String[3];
        values[0]= "0";
        values[1]= "4";
        values[2]= "0";

        Assert.assertEquals(-1.0,ForcesCalculator.calculateNforceOnBody(100,values,true));
    }


    @Test
    public void canCalculateforce() throws Exception {
        String[] values = new String[3];
        values[0]= "0";
        values[1]= "10.8";
        values[2]= "0";

        Assert.assertEquals(50.0,ForcesCalculator.calculateNforceOnBody(100,values,true));
    }

    @Test
    public void testClaculateHadInjuryCriterion() throws Exception {

        String[] values = new String[3];
        values[0]= "0";
        values[1]= "29.8";
        values[2]= "0";
        Assert.assertEquals(50.0,ForcesCalculator.calculateHadInjuryCriterion(values));

    }
}
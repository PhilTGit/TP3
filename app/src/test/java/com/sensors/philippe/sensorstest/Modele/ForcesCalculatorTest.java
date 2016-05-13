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

    private SensorEvent sensor;


    @Before
    private void inititeTest(){

        sensor = mock(SensorEvent.class);
    }

    @Test
    public void testCalculateNforceOnBody() throws Exception {

    }

    @Test
    public void testClaculateHadInjuryCriterion() throws Exception {

    }
}
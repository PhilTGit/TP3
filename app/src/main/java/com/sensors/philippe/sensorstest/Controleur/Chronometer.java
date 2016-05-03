package com.sensors.philippe.sensorstest.Controleur;

import android.os.CountDownTimer;

public class Chronometer {

    public enum ChronometerType {FINITE, INFINITE};
    private ChronometerType chronometerType;
    private ChronometerListener chronometerListener;
    private CountDownTimer timer;

    public Chronometer(final long millisInFuture, long countDownInterval, ChronometerType type, ChronometerListener listener) {
        this.chronometerListener = listener;
        this.chronometerType = type;
        this.timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                chronometerListener.update(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                chronometerListener.update(0);
                if (chronometerType == ChronometerType.INFINITE)
                    this.start();
            }
        };
    }

    public void start(){
        this.timer.start();
    }
}

package com.sensors.philippe.sensorstest.Controleur;

import android.os.CountDownTimer;

public class Chronometer {

    public enum ChronometerType {FINITE, INFINITE};
    private ChronometerType chronometerType;
    private ChronometerListener chronometerListener;
    private CountDownTimer timer;

    public Chronometer(final String id, final long millisInFuture, long countDownInterval, ChronometerType type, ChronometerListener listener) {
        this.chronometerListener = listener;
        this.chronometerType = type;
        this.timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                chronometerListener.update(id, millisUntilFinished);
            }

            @Override
            public void onFinish() {
                chronometerListener.onFinish(id);
                if (chronometerType == ChronometerType.INFINITE)
                    this.start();
            }
        };
    }

    public void start(){
        this.timer.start();
    }

    public void stop() {this.timer.cancel();}
}
package com.sensors.philippe.sensorstest.Vue;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sensors.philippe.sensorstest.Controleur.Chronometer;
import com.sensors.philippe.sensorstest.Controleur.ChronometerListener;
import com.sensors.philippe.sensorstest.R;

import java.util.ArrayList;
import java.util.List;

public class AlertActivity extends AppCompatActivity implements ChronometerListener {

    public static final String AUTO_CALL_TIMER = "autoCallTimer";
    public static final String FLASHING_TIMER = "flashingTimer";

    private TextView tv_timer;
    private LinearLayout llayout_flashing;

    private Chronometer callTimer;

    private Chronometer backGroundTimer;
    private int bgColorCode;
    private int bgColorCodeDirection;
    private List<Integer> bgcolors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        tv_timer = (TextView)findViewById(R.id.tv_timer);
        tv_timer.setText("30");

        llayout_flashing = (LinearLayout)findViewById(R.id.llayout_flashing);
        llayout_flashing.setBackgroundColor(Color.parseColor("#ffffff"));

        callTimer = new Chronometer(AUTO_CALL_TIMER, 30000, 1000, Chronometer.ChronometerType.FINITE, this);

        backGroundTimer = new Chronometer(FLASHING_TIMER, 50, 50, Chronometer.ChronometerType.INFINITE, this);
        bgColorCode = 0;
        bgColorCodeDirection = -1;
        bgcolors = new ArrayList<>();

        initializeColorsList();
        refreshView();
        updateBgColorCode();
    }

    @Override
    protected void onResume() {
        super.onResume();

        callTimer.start();
        backGroundTimer.start();
    }

    @Override
    public void update(String id, long millisUntilFinished) {
        if (id.equals(AUTO_CALL_TIMER)) {
            long secondsUntilFinished = millisUntilFinished / 1000;
            int secondsLeft = Integer.valueOf(String.valueOf(secondsUntilFinished));
            tv_timer.setText(String.valueOf(secondsLeft));
        }
    }

    @Override
    public void onFinish(String id) {
        if (id.equals(FLASHING_TIMER)) {
            refreshView();
            updateBgColorCode();
        } else if (id.equals(AUTO_CALL_TIMER)) {
            setTextToCallingNow();
            //TODO Appelé les secours.
        }
    }

    public void onClickCallNowBtn(View view) {
        setTextToCallingNow();
        this.callTimer.stop();
        //TODO Appeler les secours.
    }


    /**
     * #region Gestion du flash de l'écran
     * */
    public void onClickCancelBtn(View view) {
        //TODO Retourner à la page principale. Enregistrer la collision quand même en indiquant que l'appel a été annulé.
        startActivity(new Intent(getBaseContext(), MainActivity.class));
    }

    private void refreshView(){
        llayout_flashing.setBackgroundColor(this.bgcolors.get(this.bgColorCode));
    }

    private void updateBgColorCode() {
        if (bgColorCode <= 0 || bgColorCode >= 7) {
            this.bgColorCodeDirection *= -1;
        }
        this.bgColorCode += this.bgColorCodeDirection;
    }

    private void initializeColorsList() {
        this.bgcolors.add(0, Color.parseColor("#ef9a9a"));
        this.bgcolors.add(1, Color.parseColor("#e57373"));
        this.bgcolors.add(2, Color.parseColor("#ef5350"));
        this.bgcolors.add(3, Color.parseColor("#f44336"));
        this.bgcolors.add(4, Color.parseColor("#e53935"));
        this.bgcolors.add(5, Color.parseColor("#d32f2f"));
        this.bgcolors.add(6, Color.parseColor("#c62828"));
        this.bgcolors.add(7, Color.parseColor("#b71c1c"));
    }
    /**
     * #endregion
     * */

    private void setTextToCallingNow() {
        ViewGroup.LayoutParams timerLayout = tv_timer.getLayoutParams();
        timerLayout.height = ViewGroup.LayoutParams.MATCH_PARENT;
        tv_timer.setLayoutParams(timerLayout);
        tv_timer.setText(R.string.alert_callingNow);
    }
}

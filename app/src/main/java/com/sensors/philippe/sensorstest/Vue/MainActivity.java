package com.sensors.philippe.sensorstest.Vue;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sensors.philippe.sensorstest.Controleur.Chronometer;
import com.sensors.philippe.sensorstest.Controleur.ChronometerListener;
import com.sensors.philippe.sensorstest.Modele.Account;
import com.sensors.philippe.sensorstest.R;

public class MainActivity extends AppCompatActivity implements ChronometerListener, SensorEventListener{

    private TextView tv_X;
    private TextView tv_Y;
    private TextView tv_Z;
    private TextView login;

    private Button btnLogin;
    private Button btnRegister;

    private Chronometer chronometer;
    private boolean sensorToUpdate;

    private SensorManager smanager;
    private Sensor accelerometer;

    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_X = (TextView)findViewById(R.id.tv_X);
        tv_Y = (TextView)findViewById(R.id.tv_Y);
        tv_Z = (TextView)findViewById(R.id.tv_Z);
        login = (TextView)findViewById(R.id.tvLogin);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        this.chronometer = new Chronometer(150, 150, Chronometer.ChronometerType.INFINITE, this);
        sensorToUpdate = true;

        smanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        this.account = null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.account = new Account(100, "Tremblay", "Philippe", "Awe");
    }

    @Override
    protected void onResume() {
        super.onResume();
        smanager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        refreshView();
        this.chronometer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }

    public void onClickBtnLogin(View view) {
        //TODO Extraire les chaînes de caractères
        if (this.btnLogin.getText().equals("Déconnexion")) {
            this.account = null;
        } else if (this.btnLogin.getText().equals("Se connecter")) {
            //TODO Connecter l'utilisateur.
            this.account = new Account(100, "Tremblay", "Philippe", "Awe");
        }
        refreshView();
    }

    public void onClickBtnRegister(View view) {
        startActivity(new Intent(getApplicationContext(), Inscription.class));
    }

    private void refreshView(){
        if (this.account != null) {
            this.login.setText(this.account.getAccountID());
            this.btnLogin.setText("Déconnexion");
            this.btnRegister.setVisibility(View.INVISIBLE);
        } else {
            this.login.setText("Non connecter");
            this.btnLogin.setText("Se connecter");
            this.btnRegister.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update(long millisUntilFinished) {
        if (millisUntilFinished == 0)
            sensorToUpdate = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensorToUpdate) {
            tv_X.setText("X: " + Float.toString(event.values[0]));
            tv_Y.setText("Y: " + Float.toString(event.values[1]));
            tv_Z.setText("Z: " + Float.toString(event.values[2]));
            sensorToUpdate = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

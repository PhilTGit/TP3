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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sensors.philippe.sensorstest.Controleur.Chronometer;
import com.sensors.philippe.sensorstest.Controleur.ChronometerListener;
import com.sensors.philippe.sensorstest.Modele.Account;
import com.sensors.philippe.sensorstest.Modele.Collision;
import com.sensors.philippe.sensorstest.Modele.DatabaseManager;
import com.sensors.philippe.sensorstest.Modele.DatabaseManagerListener;
import com.sensors.philippe.sensorstest.Modele.ForcesCalculator;
import com.sensors.philippe.sensorstest.Modele.RequestType;
import com.sensors.philippe.sensorstest.R;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements ChronometerListener, SensorEventListener, DatabaseManagerListener{

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

    //HIC>300 appeller, car c'est la limite supportable avant d'avoir de gros risque pour un adulte moyen
    private static int HIC =700;
    //3300 Newtons est la force nécessaire pour avoir une chance sur 4 de briser une cote et
    //une quasi certidude de la félée.
    private static int FORCE_NEED_TO_CALL = 3300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String a = String.valueOf(R.string.alert_alertText);

        tv_X = (TextView)findViewById(R.id.tv_X);
        tv_Y = (TextView)findViewById(R.id.tv_Y);
        tv_Z = (TextView)findViewById(R.id.tv_Z);
        login = (TextView)findViewById(R.id.tvLogin);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        this.chronometer = new Chronometer("updateRateTimer", 150, 150, Chronometer.ChronometerType.INFINITE, this);
        sensorToUpdate = true;

        smanager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        Bundle extras = this.getIntent().getExtras();

        if (extras != null) {
            String accountAsString = extras.getString("ACCOUNT");
            if (accountAsString != null) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    this.account = mapper.readValue(accountAsString, Account.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                this.account = null;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO Retirer ceci.
        //startActivity(new Intent(getBaseContext(), AlertActivity.class));
        //TODO Retirer ceci.
        //this.account = new Account("Awe", "AsYouCommand", "Tremblay", "Philippe", "00000000", 100, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        smanager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        this.chronometer.start();
        refreshView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }

    public void onClickBtnLogin(View view) {

        if (this.btnLogin.getText().toString().equals(getResources().getString(R.string.main_connectionBtnText_connected))) {
            this.account = null;
        } else if (this.btnLogin.getText().toString().equals(getResources().getString(R.string.main_connectionBtnText_notConnected))) {
            startActivity(new Intent(getBaseContext(), LoginActivity.class));
        }
        refreshView();
    }

    public void onClickBtnRegister(View view) {
        startActivity(new Intent(getApplicationContext(), Inscription.class));
    }

    private void refreshView(){
        if (this.account != null) {
            this.login.setText(this.account.getAccountID());
            this.btnLogin.setText(R.string.main_connectionBtnText_connected);
            this.btnRegister.setVisibility(View.INVISIBLE);
        } else {
            this.login.setText(R.string.main_idText_notConnected);
            this.btnLogin.setText(R.string.main_connectionBtnText_notConnected);
            this.btnRegister.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void update(String id, long millisUntilFinished) {
        if (millisUntilFinished == 0)
            sensorToUpdate = true;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (sensorToUpdate) {

            String XForce = Float.toString(event.values[0]);
            String YForce = Float.toString(event.values[1]);
            String ZForce = Float.toString(event.values[2]);

            String[] valueOfAccelerometer = new String[3];
            valueOfAccelerometer[0] = XForce;
            valueOfAccelerometer[1] = YForce;
            valueOfAccelerometer[2] = ZForce;

            tv_X.setText("X: " + XForce);
            tv_Y.setText("Y: " + YForce);
            tv_Z.setText("Z: " + ZForce);
            sensorToUpdate = false;

            float weight;
            boolean seatBeltAlwaysOn;
            if (this.account != null) {
                weight = account.getWeight();
                seatBeltAlwaysOn = account.isSeatBeltAlwaysOn();
            } else {
                weight = 70;
                seatBeltAlwaysOn = false;
            }

            if(ForcesCalculator.calculateNforceOnBody(weight, valueOfAccelerometer,
                    seatBeltAlwaysOn) >= FORCE_NEED_TO_CALL ||
                    ForcesCalculator.calculateNforceOnBody(weight,
                            valueOfAccelerometer, seatBeltAlwaysOn) == -1){
                //TODO enregistrer la collision
            }

            if(ForcesCalculator.calculateHeadInjuryCriterion(valueOfAccelerometer) >= HIC ||
                    ForcesCalculator.calculateHeadInjuryCriterion(valueOfAccelerometer) ==-1  ){
                //TODO enregistrer la collision
            }
        }
    }

    @Override
     public void onFinish(String id) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //TODO Supprimer ceci
    public void pretendCollision(View view) {
        Collision falseCollision = new Collision(new java.sql.Date(1L), 765, "Awe", true);
        ObjectMapper mapper = new ObjectMapper();
        String accountAsString = "";
        try {
            accountAsString = mapper.writeValueAsString(falseCollision);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DatabaseManager.requestDatabase(this, RequestType.CREATE_COLLISION, accountAsString);
    }

    @Override
    public void requestResult(RequestType requestType, Object object) {
        
    }

    private void onCollision(){
        Intent alertIntent = new Intent(getApplicationContext(), AlertActivity.class);
        alertIntent.putExtra("PHONE_NUMBER",account.getEmergencyNumber());
        startActivity(alertIntent);
    }
}

package com.sensors.philippe.sensorstest.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sensors.philippe.sensorstest.R;

public class Inscription extends AppCompatActivity {

    private EditText et_Identifiant;
    private EditText et_Name;
    private EditText et_FirstName;
    private EditText et_Weight;

    private Button btnBack;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        et_Identifiant = (EditText)findViewById(R.id.et_id);
        et_Name = (EditText)findViewById(R.id.et_name);
        et_FirstName = (EditText)findViewById(R.id.et_firstName);
        et_Weight = (EditText)findViewById(R.id.et_weight);
    }

    public void onClickBtnBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void onClickBtnConfirm(View view) {
        //TODO Créer une nouvelle accompte dans la base de donner avec les informations entrer des les EditText.
    }
}

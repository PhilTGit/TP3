package com.sensors.philippe.sensorstest.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sensors.philippe.sensorstest.Controleur.Validator;
import com.sensors.philippe.sensorstest.Modele.Account;
import com.sensors.philippe.sensorstest.R;

public class Inscription extends AppCompatActivity {

    private EditText et_Identifiant;
    private EditText et_Name;
    private EditText et_FirstName;
    private EditText et_Weight;
    private EditText et_phoneNumber;

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
        et_phoneNumber = (EditText)findViewById(R.id.et_phoneNumber);
    }

    public void onClickBtnBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void onClickBtnConfirm(View view) {
        String id = et_Identifiant.getText().toString();
        String name = et_Name.getText().toString();
        String firstName = et_FirstName.getText().toString();
        String phoneNumber = et_phoneNumber.getText().toString();
        float weight = Float.parseFloat(et_Weight.getText().toString());




        if (Validator.validateID(id)) {
            if (Validator.validatePhoneNumber(phoneNumber)) {
                if ((name.length() > 0) && (firstName.length() > 0)) {
                    if (weight > 0) {
                        Account account = new Account(id, name, firstName, phoneNumber, weight, true);
                        //TODO Ajouter le nouveau compte dans la base de données.
                    }
                }
            }
        }
    }
}

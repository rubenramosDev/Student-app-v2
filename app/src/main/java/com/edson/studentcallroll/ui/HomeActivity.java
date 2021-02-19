package com.edson.studentcallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.edson.studentcallroll.R;

public class HomeActivity extends AppCompatActivity {

    CardView cardView3;
    TextView txtVNom;
    TextView txtVPrenom;
    private SharedPreferences shPref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupComponents();
    }

    public void setupComponents() {
        shPref = HomeActivity.this.getSharedPreferences("StudentCallRoll_ShPref", 0);
        userValidation();
        cardView3 = findViewById(R.id.cardView3);
        txtVNom = findViewById(R.id.txtVLastName);
        txtVPrenom = findViewById(R.id.txtVPrenom);
        txtVNom.setText(shPref.getString("last_name", null));
        txtVPrenom.setText(shPref.getString("first_name", null));
    }

    public void userValidation(){
        try {
            String token = shPref.getString("auth_token", null);
            if (token == null){
                intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        } catch (NullPointerException npe){

        }
    }

    public void startAssistsActivity(View view) {
        intent = new Intent(HomeActivity.this, AssistanceListActivity.class);
        startActivity(intent);
    }

    public void startGenerateQrCodeActivity(View view) {
        intent = new Intent(HomeActivity.this, GenerateQrCodeActivity.class);
        startActivity(intent);
    }

    public void startReadQrCodeActivity(View view) {
        intent = new Intent(HomeActivity.this, ReadQrCodeActivity.class);
        startActivity(intent);
    }

    public void startSettingsActivity(View view){
        intent = new Intent(HomeActivity.this, SettingsActivity.class);
        startActivity(intent);
    }


}
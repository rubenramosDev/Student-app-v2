package com.edson.studentcallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.edson.studentcallroll.R;

import com.edson.studentcallroll.viewmodel.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {

    private EditText edTxtName;
    private EditText edTxtStudentNum;
    private EditText edTxtUser;
    private EditText edTxtPassword;

    private SettingsViewModel viewModel;
    private SharedPreferences shPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setupComponents();
        getMyInfo();
    }

    public void setupComponents() {
        edTxtName = findViewById(R.id.edTxtName);
        edTxtStudentNum = findViewById(R.id.edTxtStudentNum);
        edTxtUser = findViewById(R.id.edTxtUser);
        edTxtPassword = findViewById(R.id.edTxtPassword);
        viewModel = new ViewModelProvider(SettingsActivity.this).get(SettingsViewModel.class);
        shPref = SettingsActivity.this.getSharedPreferences("StudentCallRoll_ShPref", 0);
    }

    public void getMyInfo() {
        viewModel.setHttpStatusCode(0);
        Log.i("DATA", " POUR getMyInfo: \ntoken\t" + shPref.getString("auth_token", null) + "\nidentifier_number\t" + shPref.getString("identifier_number", null));
        viewModel.getMyInformation(shPref.getString("auth_token", null),
                Integer.parseInt(shPref.getString("identifier_number", null))).
                observe(SettingsActivity.this, (Observer<String>) myInfo -> {
                    Log.i("HTTP_STATUS", "onResponseActivity: \n" + viewModel.getHttpStatusCode());
                    if (viewModel.getHttpStatusCode() == 401 || viewModel.getHttpStatusCode() == 403) {
                        singOut(SettingsActivity.this.getCurrentFocus());
                        viewModel.setHttpStatusCode(0);
                    } else {
                        if (myInfo != null) {//&& myInfo != ""){
                            Log.i("MY_INFO", "getMyInfo: " + myInfo);
                            edTxtName.setText(viewModel.getLastName() + " " + viewModel.getFirstName());
                            edTxtStudentNum.setText(viewModel.getIdentifierNumber());
                            edTxtUser.setText(viewModel.getEmail());
                            viewModel.setHttpStatusCode(0);
                        }
                    }
                });
    }

    public void updateMyInformation(View view) {
        String email = edTxtUser.getText().toString();
        String identifierNumber = shPref.getString("identifier_number", null);
        String lastName = shPref.getString("last_name", null);
        String firstName = shPref.getString("first_name", null);
        String password = edTxtPassword.getText().toString();
        String token = shPref.getString("auth_token", null);
        viewModel.modifyPassword(token, email, identifierNumber, lastName, firstName).
                observe(SettingsActivity.this, (Observer<String>) newPassword -> {
                    if (viewModel.getHttpStatusCode() == 401 || viewModel.getHttpStatusCode() == 403) {
                        singOut(SettingsActivity.this.getCurrentFocus());
                    } else {
                        if (newPassword != null && newPassword != "") {
                            Toast.makeText(SettingsActivity.this, "Mot de passe changé !", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(SettingsActivity.this, "Une erreur s'est produite !", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void singOut(View view) {
        SharedPreferences.Editor editor = shPref.edit();
        editor.clear();//delete all data from shared preferences
        editor.commit();//commit the changes
        Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
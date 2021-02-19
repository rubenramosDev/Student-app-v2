package com.edson.studentcallroll.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edson.studentcallroll.R;
import com.edson.studentcallroll.viewmodel.SingupViewModel;

public class SingupActivity extends AppCompatActivity {

    private EditText etxtLastName;
    private EditText etxtFirstName;
    private EditText etxtStudentNum;
    private EditText etxtUser;
    private EditText etxtPassword;
    private Button btnSingup;
    private TextView txtvSingup;

    private SingupViewModel viewModel;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        setupComponents();
    }

    public void setupComponents() {
        etxtLastName = findViewById(R.id.etxtLastName);
        etxtFirstName = findViewById(R.id.etxtFirstName);
        etxtStudentNum = findViewById(R.id.etxtStudentNum);
        etxtUser = findViewById(R.id.etxtUser);
        etxtPassword = findViewById(R.id.etxtPassword);
        btnSingup = findViewById(R.id.btnSingup);
        txtvSingup = findViewById(R.id.txtvSingup);
        viewModel = new ViewModelProvider(SingupActivity.this).get(SingupViewModel.class);
        setupListeners();
    }

    public void studentSingup() {
        viewModel.singupStudent(etxtUser.getText().toString(), etxtStudentNum.getText().toString(),
                etxtLastName.getText().toString(), etxtFirstName.getText().toString(),
                etxtPassword.getText().toString()).observe(SingupActivity.this, (Observer<String>) message -> {
            Toast.makeText(SingupActivity.this, message, Toast.LENGTH_LONG).show();
        });
        startLoginActivity();
    }

    public void setupListeners(){
        etxtUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etxtUser.setError("Ecrire uniquement votre identifiant\nex. nom.prenom");
            }
        });
        btnSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentSingup();
            }
        });
        txtvSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });
    }

    public void startLoginActivity() {
        intent = new Intent(SingupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}

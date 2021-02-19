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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edson.studentcallroll.R;
import com.edson.studentcallroll.databinding.ActivityLoginBinding;
import com.edson.studentcallroll.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupComponents();
    }

    public void setupComponents() {
        viewModel = new ViewModelProvider(LoginActivity.this).get(LoginViewModel.class);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        binding.setLoginViewModel(viewModel);
    }

    public void startHomeActivity(View view) {
        try {
            viewModel.userLogin().observe(LoginActivity.this, (Observer<String>) message -> {
                String token = viewModel.getToken();
                Toast.makeText(LoginActivity.this, viewModel.message.getValue(), Toast.LENGTH_LONG).show();
                if (token != null) {
                    //shared preferences save token
                    SharedPreferences shPref = getApplicationContext().getSharedPreferences("StudentCallRoll_ShPref", 0);
                    SharedPreferences.Editor editor = shPref.edit();
                    editor.putString("auth_token", "Bearer " + token);
                    Log.i("LOGIN_ACTIVITY", token);
                    editor.putString("last_name", viewModel.getLastName());
                    editor.putString("first_name", viewModel.getFirstName());
                    editor.putString("identifier_number", viewModel.getIdentifierNumber());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
    }

    public void startSingupActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, SingupActivity.class);
        startActivity(intent);
    }

}
package com.edson.studentcallroll.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.edson.studentcallroll.R;
import com.edson.studentcallroll.viewmodel.ReadQrCodeViewModel;
import com.google.zxing.Result;

public class ReadQrCodeActivity extends AppCompatActivity {

    private int CAMERA_REQUEST_CODE = 100;
    private CodeScanner codeScanner;
    private CodeScannerView codeScannerView;
    private ReadQrCodeViewModel viewModel;
    SharedPreferences shPref;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_qr_code);
        if (ContextCompat.checkSelfPermission(ReadQrCodeActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= 23) {
                ActivityCompat.requestPermissions(ReadQrCodeActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        } else {
            setupComponents();
            initCodeScanner();
        }
    }

    public void setupComponents() {
        codeScannerView = findViewById(R.id.scanner_view);
        shPref = ReadQrCodeActivity.this.getSharedPreferences("StudentCallRoll_ShPref", 0);
        viewModel = new ViewModelProvider(ReadQrCodeActivity.this).get(ReadQrCodeViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    public void initCodeScanner() {
        codeScanner = new CodeScanner(this, codeScannerView);
        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(false);
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewModel.takeAssistance(shPref.getString("auth_token", null),
                                Integer.parseInt(result.getText()), shPref.getString("identifier_number", null))
                                .observe(ReadQrCodeActivity.this, (Observer<String>) code -> {
                                    if (viewModel.getHttpStatusCode() == 401 || viewModel.getHttpStatusCode() == 403) {
                                        singOut();
                                    } else {

                                        if (code == "200") {
                                            Toast.makeText(ReadQrCodeActivity.this, code, Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(ReadQrCodeActivity.this, code, Toast.LENGTH_LONG).show();
                                        }
                                        finish();
                                    }
                                });
                    }
                });
            }
        });
    }

    public void singOut() {
        SharedPreferences.Editor editor = shPref.edit();
        editor.clear();//delete all data from shared preferences
        editor.commit();//commit the changes
        intent = new Intent(ReadQrCodeActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
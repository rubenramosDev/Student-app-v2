package com.edson.studentcallroll.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.edson.studentcallroll.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQrCodeActivity extends AppCompatActivity {

    private ImageView imgVQRCode;
    private TextView txtVIdentifierNumber;
    private Bitmap bitmap;
    private QRGEncoder qrgEncoder;
    SharedPreferences shPref;
    String identifierNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr_code);
        setupComponents();
        createQR();
    }

    public void setupComponents(){
        imgVQRCode = findViewById(R.id.imgVQRCode);
        txtVIdentifierNumber = findViewById(R.id.txtVIdentifierNumber);
        shPref = GenerateQrCodeActivity.this.getSharedPreferences("StudentCallRoll_ShPref", 0);
        identifierNumber = shPref.getString("identifier_number", null);
    }

    public void createQR() {
        try {
            txtVIdentifierNumber.setText(identifierNumber);
            qrgEncoder = new QRGEncoder(identifierNumber, null, QRGContents.Type.TEXT, 500);
            qrgEncoder.setColorBlack(Color.parseColor("#001558"));
            bitmap = qrgEncoder.getBitmap();
            imgVQRCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
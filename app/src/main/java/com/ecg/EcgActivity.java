package com.ecg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rxjava2.test.R;

public class EcgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg);
        Electrocardiogram electrocardiogram
                = (Electrocardiogram)findViewById(R.id.electrocardiogram);
        electrocardiogram.startDraw();

        electrocardiogram.addData();
    }
}

package com.coordinator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rxjava2.test.R;

public class CoordinatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);
        getSupportActionBar().hide();







    }
}

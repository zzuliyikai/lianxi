package com.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rxjava2.test.R;

public class Theme1Activity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme1);
        initData();

    }

    private void initData() {
        toolbar = findViewById(R.id.toolbar);


    }
}

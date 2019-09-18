package com.surfaceView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rxjava2.test.R;

public class SurfaceViewActivity extends AppCompatActivity {

    private MySurfaceView mSf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view);
        mSf = findViewById(R.id.sf);

    }
}

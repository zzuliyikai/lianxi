package com.exoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.rxjava2.test.R;

public class ExoplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exoplayer);



        RelativeLayout rl = findViewById(R.id.rl);
        ExoPlayerVideoView playerVideoView = new ExoPlayerVideoView(this);
        rl.addView(playerVideoView);

        playerVideoView.setResourcePath("http://test.yungeshidai.com/material/eec76c455d081e4498c0ad7a55fb67e1.mp4");
        playerVideoView.playOn();

    }
}

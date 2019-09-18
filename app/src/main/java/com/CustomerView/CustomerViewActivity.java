package com.CustomerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rxjava2.test.R;

public class CustomerViewActivity extends AppCompatActivity {

    private CountDownProgressBar mCdpCountdown;
    private FrameLayout ll;
    private CountDownProgressBar countDownProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        ll = findViewById(R.id.ll);
        countDownProgressBar = new CountDownProgressBar(this);

    }

    public void start(View view) {

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ll.addView(countDownProgressBar,params);

        countDownProgressBar.setDuration(10000, new CountDownProgressBar.OnFinishListener() {
            @Override
            public void onFinish() {

                ll.removeView(countDownProgressBar);

            }
        });


    }
}

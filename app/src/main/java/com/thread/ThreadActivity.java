package com.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rxjava2.test.R;

public class ThreadActivity extends AppCompatActivity {

    private MyThread myThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        myThread = new MyThread();
        myThread.start();

    }

    public void clearCount(View view) {

        myThread.clearCount();
    }
}

package com.thread;

import android.util.Log;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    private static final String TAG = "MyThread";
    private volatile int count = 0;
    private final ReentrantLock mainLock = new ReentrantLock();
    private final Condition available = mainLock.newCondition();

    @Override
    public void run() {
        super.run();
        mainLock.lock();
        try {
            startCount();
        }finally {
            mainLock.unlock();
        }
    }

    private void startCount() {
        Log.d(TAG,"run" + Thread.currentThread().getName());
        while (true) {
            try {
                Thread.sleep(3000);
                count++;
                Log.d(TAG,"count = " + count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearCount(){
        Log.d(TAG,Thread.currentThread().getName());
        count = 0;
    }
}

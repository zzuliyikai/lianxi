package com.rxjava.imp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.rxjava2.test.R;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "TestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void test(View view) {

        Observable.create(new Observable.OnSubercibe<String>() {
            @Override
            public void call(Subscriber subscriber) {
                subscriber.onNext("hello");

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onNext(String value) {

                Log.d(TAG, "onNext: "+value);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }

    public void test1(View view) {

        io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.d(TAG,"create"+Thread.currentThread().getName());
                e.onNext("yikai");

            }
        }).observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG,"doOnNext"+Thread.currentThread().getName());
            }
        }).observeOn(Schedulers.computation())
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG,"subscribe"+Thread.currentThread().getName());
            }
        });




    }
}

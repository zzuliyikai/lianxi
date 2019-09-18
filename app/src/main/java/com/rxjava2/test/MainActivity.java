package com.rxjava2.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private List<String> bonds6Month = new ArrayList<>();
    private List<String> bonds3Month = new ArrayList<>();
    private List<String> bonds1Month = new ArrayList<>();
    private List<String> bonds1Week = new ArrayList<>();
    private List<String> bonds1Year = new ArrayList<>();
    private List<String> bonds3Year = new ArrayList<>();

    private String ss = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RxBus.getInstance().
                toObservableSticky(String.class)
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai doOnNext", Thread.currentThread().getName());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai onnext", Thread.currentThread().getName());
                    }
                });

    }

    public void onNextPage(View view) {
        RxBus.getInstance().sendSticky("Hello sticky");
//        startActivity(new Intent(this,SecondActivity.class));

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return String.valueOf(integer);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("yikai", s);
                    }
                });


        bonds3Month.add("006011");
        bonds3Month.add("005212");
        bonds3Month.add("005578");
        bonds3Month.add("001199");
        bonds3Month.add("002101");
        bonds3Month.add("005273");
        bonds3Month.add("001200");
        bonds3Month.add("005451");
        bonds3Month.add("002102");
        bonds3Month.add("000185");
        bonds3Month.add("005284");

        bonds1Month.add("006258");
        bonds1Month.add("005212");
        bonds1Month.add("573003");
        bonds1Month.add("000630");
        bonds1Month.add("000629");
        bonds1Month.add("001578");
        bonds1Month.add("160621");
        bonds1Month.add("000563");
        bonds1Month.add("000564");
        bonds1Month.add("261102");
        bonds1Month.add("005577");


        bonds1Week.add("165807");
        bonds1Week.add("002554");
        bonds1Week.add("005451");
        bonds1Week.add("005452");
        bonds1Week.add("675081");
        bonds1Week.add("002719");
        bonds1Week.add("002138");
        bonds1Week.add("675083");
        bonds1Week.add("160602");
        bonds1Week.add("000032");
        bonds1Week.add("162712");


        bonds6Month.add("004957");
        bonds6Month.add("003349");
        bonds6Month.add("003406");
        bonds6Month.add("001578");
        bonds6Month.add("005467");
        bonds6Month.add("003990");
        bonds6Month.add("000130");
        bonds6Month.add("090002");
        bonds6Month.add("092002");
        bonds6Month.add("000131");
        bonds6Month.add("005025");


        bonds1Year.add("003996");
        bonds1Year.add("003009");
        bonds1Year.add("003010");
        bonds1Year.add("003349");
        bonds1Year.add("003188");
        bonds1Year.add("003407");
        bonds1Year.add("003408");
        bonds1Year.add("003406");
        bonds1Year.add("003818");
        bonds1Year.add("002246");
        bonds1Year.add("004118");


        bonds3Year.add("001367");
        bonds3Year.add("202101");
        bonds3Year.add("000973");
        bonds3Year.add("164210");
        bonds3Year.add("164703");
        bonds3Year.add("000286");
        bonds3Year.add("161716");
        bonds3Year.add("050023");
        bonds3Year.add("167501");
        bonds3Year.add("090002");
        bonds3Year.add("217022");


    }
}

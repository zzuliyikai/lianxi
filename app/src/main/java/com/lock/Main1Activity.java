package com.lock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.rxjava2.test.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Main1Activity extends AppCompatActivity {

    private String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

//        Observable.concatDelayError(Arrays.asList(getStringObservable(),getIntegerObservable()))
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//
//                        Log.d("yikai",s);
//
//                    }
//                });
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//
//                emitter.onNext(1);
//                emitter.onNext(2);
//                emitter.onNext(3);
//                emitter.onComplete();
//            }
//        })
//                .map(new Function<Integer, String>() {
//                    @Override
//                    public String apply(Integer integer) throws Exception {
//                        return integer+"";
//                    }
//                })
//                .doOnNext(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//
//                    }
//                })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String o) throws Exception {
//
//
//
//            }
//        });

        Integer aa = new Integer(1);
        getCount(aa);
        aa++;
        Log.d("yikai",stationPositionCount.get(1L)+"");

    }

    private Observable<String> getStringObservable(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onComplete();

            }
        });
    }

    private Observable<String> getIntegerObservable(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("2");
                emitter.onComplete();
            }
        });
    }

    Map<Long, Integer> stationPositionCount = new HashMap<>();
    public void getCount(Integer count) {

        stationPositionCount.put(1L,count);

    }
}

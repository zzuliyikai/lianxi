package com.lock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.rxjava2.test.R;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        WebView wv = findViewById(R.id.wv);

        wv.loadUrl("https://www.sina.com.cn");

        wv.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



    }

    public void start(View view) {

        io.reactivex.Observable<Object> objectObservable = io.reactivex.Observable.concatArrayDelayError(new io.reactivex.Observable<Object>() {
            @Override
            protected void subscribeActual(Observer<? super Object> observer) {
                observer.onComplete();
            }
        }, new io.reactivex.Observable<Object>() {
            @Override
            protected void subscribeActual(Observer<? super Object> observer) {
                observer.onError(new NoSuchFieldError());
            }
        })
                .firstElement()
                .toObservable();


        objectObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Toast.makeText(Main3Activity.this, "wwwwwwww", Toast.LENGTH_SHORT).show();

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {

                    }
                });
    }

    public void end(View view) {


    }
}

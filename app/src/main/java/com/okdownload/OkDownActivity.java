package com.okdownload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rxjava2.test.R;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkDownActivity extends AppCompatActivity {

    private static final long DEFAULT_TIMEOUT = 50;
    public static String PLAYER_API_OFFICIAL_HOST = "http://saas.oohlink.com/website/play/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_down);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        Retrofit mRetrofit = new Retrofit.Builder().client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(PLAYER_API_OFFICIAL_HOST)
                .build();

        DownloadApi mDownloadApi = mRetrofit.create(DownloadApi.class);

        mDownloadApi.download("bytes=" + 0 + "-",
               "")
                /*指定线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {

                        Log.d("yikai","onnext");

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        Log.d("yikai","error"+throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                        Log.d("yikai","onplete");

                    }
                });

    }
}

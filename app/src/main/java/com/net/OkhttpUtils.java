package com.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/12/29.
 */

public class OkhttpUtils {
    private OkhttpUtils okhttpUtils;
    private OkHttpClient mOkHttpClient;
    private OkhttpUtils(){
    }

    public OkhttpUtils getInstance(){
        if (okhttpUtils == null){
            synchronized (this){
                if (okhttpUtils == null){
                    okhttpUtils = new OkhttpUtils();
                }
            }
        }
        return okhttpUtils;
    }

    public void doRequest(final Request request){

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });



    }







}

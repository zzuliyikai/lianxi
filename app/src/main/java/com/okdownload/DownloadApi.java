package com.okdownload;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadApi {

    /*断点续传下载接口*/
    @GET
    @Streaming
    Observable<ResponseBody> download(@Header("Range") String range, @Url String url);
}
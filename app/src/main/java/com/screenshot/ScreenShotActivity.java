package com.screenshot;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.rxjava2.test.R;

public class ScreenShotActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);

        WebView web = findViewById(R.id.web);

        web.loadUrl("http://file.sportpro.net.cn//files/xcp.mp4");

// 通过设置WebView的settings来实现
        WebSettings settings = web.getSettings();

        // 1. 设置缓存路径 ------ application 缓存
        String cacheDirPath = getFilesDir().getAbsolutePath() + "cache/";
        settings.setAppCachePath(cacheDirPath);
        // 2. 设置缓存大小
    //    settings.setAppCacheMaxSize(PlayerConstants.WEBVIEW_CACHE_SIZE);
        // 3. 开启Application Cache存储机制
        settings.setAppCacheEnabled(true);

        // 开启DOM storage
        settings.setDomStorageEnabled(true);

        // 只需设置支持JS就自动打开IndexedDB存储机制
        settings.setJavaScriptEnabled(true);

        //设置webView的client
        web.setWebViewClient(new OOhlinkWebViewClient());
        //设置webView的
        web.setWebChromeClient(new OOhlinkWebChromClient());

    }





    public static class OOhlinkWebViewClient extends WebViewClient {

        /**
         * 不打开其他浏览器，而直接从webView上打开url页面
         *
         * @param view
         * @param url
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }


        /**
         * webView 开始加载的时候load
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        /**
         * 加载失败时候调用,如果加载失败我们可以定义一个默认界面
         *
         * @param view
         * @param request
         * @param error
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);


        }

        /**
         * webView 默认不处理https
         *
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();

        }
    }

    public static class OOhlinkWebChromClient extends WebChromeClient {


        @Override
        public void onProgressChanged(WebView view, int newProgress) {


        }
    }




}

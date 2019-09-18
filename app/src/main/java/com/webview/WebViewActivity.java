package com.webview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rxjava2.test.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ImageView mIvSnap;
    private Button mBtnSnap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.wv);
        mIvSnap = findViewById(R.id.iv_sanp);
        mBtnSnap = findViewById(R.id.btn_snap);

        webView.loadUrl("http://www.baidu.com/");

        //开启h5声音
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }

            @Override public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();

            }
        });
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(WebViewActivity.this, "wwwww", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        mBtnSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvSnap.setImageBitmap(getViewBitmap(webView));
            }
        });

    }


    Bitmap getViewBitmap(WebView webView){
        int height = (int) (webView.getContentHeight() * webView.getScale());
        int width = webView.getWidth();
        int pH = webView.getHeight();
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bm);
        int top = height;
        while (top > 0) {
            if (top < pH) {
                top = 0;
            } else {
                top -= pH;
            }
            canvas.save();
            canvas.clipRect(0, top, width, top + pH);
            webView.scrollTo(0, top);
            webView.draw(canvas);
            canvas.restore();
        }
        return bm;
    }
}

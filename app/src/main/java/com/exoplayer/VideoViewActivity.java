package com.exoplayer;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.rxjava2.test.R;

public class VideoViewActivity extends AppCompatActivity {

    private ImageView mIvSnap;
    private Button mBtnSnap;
    private PlayerVideoView mPlayervideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        mIvSnap = findViewById(R.id.iv_sanp);
        mBtnSnap = findViewById(R.id.btn_snap);
        mPlayervideo = findViewById(R.id.playervideo);


        /**
         * 为 VideoView 视图设置媒体控制器，设置了之后就会自动由进度条、前进、后退等操作
         */
        mPlayervideo.setMediaController(new MediaController(this));

        mPlayervideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {

            }
        });

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/oohlink/player/.screen/www";
        mPlayervideo.setResourcePath(path);
        mPlayervideo.playOn();

        mBtnSnap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediaMetadataRetriever rev = new MediaMetadataRetriever();
                Uri uri = Uri.parse(path);
                //这里第一个参数需要Context，传this指针
                rev.setDataSource(VideoViewActivity.this, uri);
                Bitmap bitmap = rev.getFrameAtTime(8 * 1000,
                        MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
                mIvSnap.setImageBitmap(bitmap);
            }
        });
    }
}

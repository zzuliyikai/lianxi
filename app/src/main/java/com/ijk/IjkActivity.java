package com.ijk;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.exoplayer.ExoPlayerVideoView;
import com.ijk.media.VideoPlayerIJK;
import com.ijk.media.VideoPlayerListener;
import com.rxjava2.test.R;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IjkActivity extends AppCompatActivity {

    private VideoView videoView;
    private VideoPlayerIJK ijkPlayer;
    private String path;
    private ExoPlayerVideoView playerVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk);
        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/qqqq";

     //   bindVideoViews();

        bindIjkViews();

 //       bindexoplayer();
    }

    private void bindexoplayer() {

     //   playerVideoView = findViewById(R.id.playerv);
        playerVideoView.setResourcePath(path);
        playerVideoView.playOn();
    }

    private void bindIjkViews() {
        //加载so文件
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }

        ijkPlayer = findViewById(R.id.ijk_player);

        ijkPlayer.setListener(new VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
            }

            @Override
            public void onCompletion(IMediaPlayer mp) {
                mp.seekTo(0);
                mp.start();
            }

            @Override
            public boolean onError(IMediaPlayer mp, int what, int extra) {
                Log.d("yikai",mp.getDataSource());
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer mp, int what, int extra) {

                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer mp) {
                mp.start();
            }

            @Override
            public void onSeekComplete(IMediaPlayer mp) {

            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sar_num, int sar_den) {
                //获取到视频的宽和高
            }
        });

        ijkPlayer.setVideoPath(path);

    }

    private void bindVideoViews() {
     //   videoView = findViewById(R.id.vv);

        /**播放 res/raw 目录下的文件
         * android.resource:// ：前缀固定
         * com.example.administrator.helloworld：为当前类的所在的包路径，可以使用 String packageName = getPackageName(); 动态获取
         * R.raw.la_isla：最后接 res/raw 目录中的文件名
         * */




        Toast.makeText(this, "path"+path, Toast.LENGTH_SHORT).show();

        Log.d("yikai1",path);


     //   videoView.setVideoURI(Uri.parse("http://test.yungeshidai.com/material/eec76c455d081e4498c0ad7a55fb67e1.mp4"));

        videoView.setVideoPath(path);

        /**
         * 为 VideoView 视图设置媒体控制器，设置了之后就会自动由进度条、前进、后退等操作
         */
        videoView.setMediaController(new MediaController(this));

        /**视频准备完成时回调
         * */
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
            }
        });

        /**
         * 视频播放完成时回调
         */
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                /**播放完成时，再次循环播放*/
                videoView.start();
            }
        });

        /**
         * 视频播放发送错误时回调
         */
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });

        /**开始播放视频
         * */
        videoView.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        IjkMediaPlayer.native_profileEnd();
    }
}

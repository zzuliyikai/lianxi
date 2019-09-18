package com.exoplayer;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.VideoView;

public class PlayerVideoView extends VideoView
        implements PlayerView {
    private String mCurrentPath;

    public PlayerVideoView(Context context) {
        this(context,null);
    }

    public PlayerVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public PlayerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setResourcePath(String path) {
        this.mCurrentPath = path;
    }

    @Override
    public void playOn() {
        requestFocus();
        setVisibility(View.VISIBLE);
        playerVideo();
    }

    private void playerVideo() {
        setVideoPath(mCurrentPath);
        start();
    }

    @Override
    public void stopOff() {
        setVisibility(View.INVISIBLE);
        pause();
        stopPlayback();
    }

    @Override
    public void release() {
        setVisibility(View.INVISIBLE);
        if (isPlaying()){
            pause();
            stopPlayback();
        }
        setOnCompletionListener(null);
        setOnPreparedListener(null);
    }
}

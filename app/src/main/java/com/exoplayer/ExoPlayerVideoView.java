package com.exoplayer;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

/**
 * Created by gaowen on 2017/7/18.
 */

public class ExoPlayerVideoView extends com.google.android.exoplayer2.ui.PlayerView
        implements PlayerView {

    private static final String TAG = "PlayerVideoView";

    private String mVideoPath;
    private Context mContext;
    private SimpleExoPlayer player;

    public ExoPlayerVideoView(Context context) {
        this(context, null);
    }

    public ExoPlayerVideoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExoPlayerVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setUseController(false);
        createExoPlayer();
        setKeepContentOnPlayerReset(true);
    }

    @Override
    public void playOn() {
        requestFocus();
        setVisibility(View.VISIBLE);
        playerVideo();
    }

    @Override
    public void stopOff() {
        setVisibility(View.INVISIBLE);
        stopOffPlayer();
    }

    @Override
    public void release() {
        setVisibility(View.GONE);
        if (player != null) {
            setPlayer(null);
            player.release();
            player = null;
        }
    }

    @Override
    public void setResourcePath(String path) {
        this.mVideoPath = path;
    }

    private void createExoPlayer() {
        player = ExoPlayerFactory.newSimpleInstance(mContext);
        setPlayer(player);
        player.setPlayWhenReady(true);
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
    }

    public void addPlayerListener(Player.EventListener listener) {
        player.addListener(listener);
    }

    private void playerVideo() {
        MediaSource mediaSource = buildMediaSource(Uri.parse(mVideoPath));
        // Prepare the player with the source.
        player.prepare(mediaSource);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(mContext, "oohlink")).
                createMediaSource(uri);
    }

    private void stopOffPlayer() {
        if (player != null) {
            player.stop(true);
        }
    }
}

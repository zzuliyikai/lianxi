package com.exoplayer;

/**
 * Created by gaowen on 2017/7/18.
 */

public interface PlayerView {
    void setResourcePath(String path);
    void playOn();
    void stopOff();
    void release();
}

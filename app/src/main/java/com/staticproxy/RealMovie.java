package com.staticproxy;

public class RealMovie implements Movie {
    @Override
    public void play() {
        System.out.println("开始播放电影《哪吒》");
    }
}

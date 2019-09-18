package com.staticproxy;

public class ProxyMovie implements Movie {

    RealMovie mRealMovie;
    public ProxyMovie(RealMovie realMovie) {
        mRealMovie = realMovie;
    }

    @Override
    public void play() {
        playBefore();
        mRealMovie.play();
        playAfter();
    }

    private void playBefore() {
        System.out.println("电影开始之前");
    }

    private void playAfter() {
        System.out.println("电影开始以后");
    }
}

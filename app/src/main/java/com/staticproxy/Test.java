package com.staticproxy;

public class Test {

    public static void main(String[] arg0){

        ProxyMovie proxyMovie = new ProxyMovie(new RealMovie());

        proxyMovie.play();



    }
}

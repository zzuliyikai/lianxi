package com.reflect;

public class Car {

    private String mMame;

    private Color mColor;

    public enum Color{
        RED,
        BLUE,
        GREEN;
    }

    public Car(String mMame) {
        this.mMame = mMame;
    }

    public void driver(){
        System.out.println("我要开车去西藏，go go go");
    }

    @Override
    public String toString() {
        return "Car{" +
                "mMame='" + mMame + '\'' +
                ", mColor=" + mColor +
                '}';
    }

    class BWM{

    }

}


class BBA{

}

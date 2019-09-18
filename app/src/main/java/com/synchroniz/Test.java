package com.synchroniz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {


    public static void main(String[] arg0){
        Test test = new Test();
        new MyThread(test).start();
        new MyThread1(test).start();
    }


    public synchronized void getString(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("wwwwww" + Thread.currentThread().getName());
    }


    public void add(){



    }

}

class MyThread extends Thread{
    Test mTest;
    public MyThread(Test test){
        this.mTest = test;
    }

    @Override
    public void run() {
        super.run();
        mTest.getString();
    }
}


class MyThread1 extends Thread{

    Test mTest;
    public MyThread1(Test test){
        this.mTest = test;
    }

    @Override
    public void run() {
        super.run();
        mTest.getString();
    }
}
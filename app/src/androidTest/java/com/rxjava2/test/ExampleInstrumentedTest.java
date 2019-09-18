package com.rxjava2.test;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        User user = new User("yikai",18);
        User user1 = new User("yikai",18);

        List<User> arrayList = new ArrayList<>();
        List<User> arrayList1 = new ArrayList<>();

        arrayList.add(user);
        arrayList1.add(user1);

        Log.d("yikai",arrayList.containsAll(arrayList1)+"");


    }
}

class User{
    private String name;
    private int age;
    public User(String name,int age){
        this.name = name;
        this.age = age;
    }
}

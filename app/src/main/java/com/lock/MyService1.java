package com.lock;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService1 extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("yikai","onBind");
      return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("yikai","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("yikai","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends Binder{

        public String getString(){

            return "hello";
        }

    }

}

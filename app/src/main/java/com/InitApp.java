package com;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class InitApp extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initRealm();
    }

    private void initRealm() {
        Realm.init(mContext);
        RealmConfiguration config = new RealmConfiguration.Builder()
                //文件名
                .name("myrealm.realm")
                //版本号
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}

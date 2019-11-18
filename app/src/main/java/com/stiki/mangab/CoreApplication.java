package com.stiki.mangab;

import android.app.Application;


public class CoreApplication extends Application {
    private static CoreApplication app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

//        Realm.init(this);
//
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name("mangab.realm")
//                .schemaVersion(0)
//                .build();
//
//        Realm.setDefaultConfiguration(config);
    }
}

package br.com.accera.starwarscatalog;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }

}

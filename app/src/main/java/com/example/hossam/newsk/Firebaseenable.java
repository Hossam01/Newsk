package com.example.hossam.newsk;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hossam on 5/6/2017.
 */

public class Firebaseenable extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

}

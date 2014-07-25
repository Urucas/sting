package com.urucas.sting.application;

import android.app.Application;

/**
 * Created by Urucas on 7/23/14.
 */
public class StingApp extends Application {

    private static StingApp _instance;

    public StingApp() {
        super();
        _instance = this;
    }

    public static StingApp singleton() {
        if(_instance == null) {
            _instance = new StingApp();
        }
        return _instance;
    }
}

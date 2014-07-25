package com.urucas.sting.application;

import android.app.Application;

import com.urucas.sting.controller.ApiController;
import com.urucas.sting.controller.PersistentController;

/**
 * Created by Urucas on 7/23/14.
 */
public class StingApp extends Application {

    private static StingApp _instance;
    private static PersistentController _persistance;
    private static ApiController _api;

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

    public static PersistentController getPersistance() {
        if(_persistance == null){
            _persistance = new PersistentController();
        }
        return _persistance;
    }

    public static ApiController getApiController(){
        if(_api == null) {
            _api = new ApiController();
        }
        return _api;
    }
}

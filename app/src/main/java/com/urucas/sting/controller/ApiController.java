package com.urucas.sting.controller;

import android.provider.Settings.Secure;

import com.urucas.sting.application.StingApp;
import com.urucas.sting.utils.Utils;

public class ApiController {

	private static String BASE_URL = "http://sting.urucas.com.ar/api";

    private static String UUID;

    private String getUUID(){
        if(UUID == null) {

            String deviceId = Secure.getString(
                    StingApp.singleton().getContentResolver(),
                    Secure.ANDROID_ID
            );
            UUID = deviceId;
        }
        return UUID;
    }
	
	private boolean isConnected() {
        //checks internet connection
		return Utils.isConnected(StingApp.singleton().getApplicationContext());
	}

}

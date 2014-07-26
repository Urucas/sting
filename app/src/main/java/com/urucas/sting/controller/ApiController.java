package com.urucas.sting.controller;

import android.content.Context;
import android.provider.Settings.Secure;

import com.urucas.sting.application.StingApp;
import com.urucas.sting.callback.LoginCallback;
import com.urucas.sting.model.CustomError;
import com.urucas.sting.model.User;
import com.urucas.sting.parser.ErrorParser;
import com.urucas.sting.parser.UserParser;
import com.urucas.sting.services.JSONRequestTask;
import com.urucas.sting.services.JSONRequestTaskHandler;
import com.urucas.sting.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

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

    public void login(Context context, String email, String pass, final LoginCallback callback) {

        if(!isConnected()) {
            callback.onError(new CustomError("NO_CONNECTION"));
            return;
        }

        String url = BASE_URL + "/login";
        String imei = getUUID();

        String nonce = Utils.generateNonce();
        String repass = imei.substring(1,2);
               repass+= Utils.md5(pass);
               repass+= nonce;
               repass = Utils.md5(repass);

        new JSONRequestTask(new JSONRequestTaskHandler() {
            @Override
            public void onSuccess(JSONObject response) {
                if(response.has("error")) {
                    callback.onError(ErrorParser.parse(response));
                    return;
                }
                User user = UserParser.parse(response);
                if(user == null){
                    callback.onError(new CustomError("ERROR_GETTING_USER"));
                    return;
                }
                try {
                    StingApp app = StingApp.singleton();
                    app.getPersistance().createUser(
                            app.getApplicationContext(),
                            user
                    );
                    callback.onSuccess();
                    return;

                }catch(Exception e){
                    callback.onError(new CustomError("ERROR_CREATING_SESSION"));
                }
            }

            @Override
            public void onSuccess(JSONArray result) {

            }

            @Override
            public void onError(String message) {
                callback.onError(new CustomError("INVALID_JSON_RESPONSE"));
            }

        }).addParam("e", email)
                .addParam("i", imei)
                .addParam("n", nonce)
                .addParam("p", repass)
                .execute(url);
    }

}

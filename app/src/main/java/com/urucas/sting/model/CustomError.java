package com.urucas.sting.model;

import android.content.Context;

import com.urucas.sting.R;
import com.urucas.sting.application.StingApp;

import java.util.HashMap;

/**
 * @copyright Urucas
 * @license   Copyright (C) 2013. All rights reserved
 * @link       http://urucas.com
 * @developers Bruno Alassia, Pamela Prosperi
 * @date {5/28/14}
**/
public class CustomError {

    private String code;

    private static HashMap<String, Integer> localizedErrors = new HashMap<String, Integer>();

    public CustomError(String code){
        localizedErrors.put("INVALID_JSON_RESPONSE", R.string.invalid_json_response);
        localizedErrors.put("INVALID_TOKEN", R.string.invalid_token);
        localizedErrors.put("USER_NOT_FOUND", R.string.user_not_found);
        localizedErrors.put("INVALID_PASSWORD", R.string.invalid_password);
        localizedErrors.put("TOKEN_CREATE_ERROR", R.string.token_create_error);
        localizedErrors.put("NO_CONNECTION", R.string.no_connection);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public String getLocalizedMessage(){
        Context context = StingApp.singleton().getApplicationContext();
        try {
            int sid = localizedErrors.get(this.code);
            String msg = context.getResources().getString(sid);
            if(msg != null) {
                return msg;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}

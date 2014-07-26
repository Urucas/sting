package com.urucas.sting.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONRequestTask extends RequestTask{

	public JSONRequestTask(JSONRequestTaskHandler _jrsh) {
		super(_jrsh);		
	}

	@Override
	protected void onPostExecute(String result) {

		if(result == null) {
			jrsh.onError("INVALID_JSON_RESPONSE");
			return;
		}

        Log.i("api result", result);

		try {
			Object json = new JSONTokener(result).nextValue();
			if (json instanceof JSONObject) {
				JSONObject response = new JSONObject(result);
				jrsh.onSuccess(response);
				return;
			}
			else if (json instanceof JSONArray) {
				JSONArray response = new JSONArray(result);
				jrsh.onSuccess(response);
				return;
			}
			jrsh.onError("INVALID_JSON_RESPONSE");
			
		} catch (JSONException e) {
			jrsh.onError(e.getMessage());
		}
	}

}

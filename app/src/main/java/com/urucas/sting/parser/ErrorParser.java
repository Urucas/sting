package com.urucas.sting.parser;

import com.urucas.sting.model.CustomError;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ErrorParser {

	public static CustomError parse(JSONObject result) {
		
		try {

			String code  = result.getString("code");
            return new CustomError(code);


		} catch (JSONException e) { e.printStackTrace(); }
		
		return new CustomError("ERROR_PARSING_JSON");
	}
	
}

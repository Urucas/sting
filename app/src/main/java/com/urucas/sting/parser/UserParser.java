package com.urucas.sting.parser;

import com.urucas.sting.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class UserParser {

	public static User parse(JSONObject result) {
		
		try {

            String token = result.getString("token");
            JSONObject userObject = result.getJSONObject("user");

			int id  = userObject.getInt("id");
            String name = userObject.getString("namespace");
            String email = userObject.getString("email");
            String pass = userObject.getString("password");

            User _user = new User();
            _user.setId(id);
            _user.setName(name);
            _user.setEmail(email);
            _user.setPass(pass);
            _user.setToken(token);

            return _user;

		} catch (JSONException e) { e.printStackTrace(); }
		
		return null;
	}
	
}

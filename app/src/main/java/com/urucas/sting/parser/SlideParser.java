package com.urucas.sting.parser;

import android.util.Log;

import com.urucas.sting.model.SlideNamespace;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class SlideParser {

    private static final String TAG_NAME = "SlideParser";

    public static SlideNamespace parse(JSONObject result) {
		
		try {
            int id = result.getInt("id");
            String name = result.getString("name");
            String desc = result.getString("descrip");
            String namespace = result.getString("chatroom");

            SlideNamespace _nsp = new SlideNamespace();
            _nsp.setId(id);
            _nsp.setName(name);
            _nsp.setDesc(desc);
            _nsp.setNamespace(namespace);

            return _nsp;

		} catch (JSONException e) {
            Log.i(TAG_NAME, "aca");
            e.printStackTrace(); }
		
		return null;
	}
	
}

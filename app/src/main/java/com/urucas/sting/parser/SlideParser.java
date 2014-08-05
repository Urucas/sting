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
            String type = result.getString("type");

            SlideNamespace _nsp = new SlideNamespace();
            _nsp.setId(id);
            _nsp.setName(name);
            _nsp.setDesc(desc);
            _nsp.setNamespace(namespace);
            _nsp.setType(type);

            return _nsp;

		} catch (JSONException e) { e.printStackTrace(); }
		
		return null;
	}
	
}

package com.urucas.sting.parser;

import com.urucas.sting.model.SlideNamespace;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Urucas on 7/28/14.
 */
public abstract class SlidesParser {

    public static ArrayList<SlideNamespace> parse(JSONObject result) {
        ArrayList<SlideNamespace> nsps = new ArrayList<SlideNamespace>();
        try {
            JSONArray slides = result.getJSONArray("slides");
            for(int i = 0; i < slides.length(); i++) {
                try {
                    JSONObject object = slides.getJSONObject(i);
                    SlideNamespace nsp = SlideParser.parse(object);
                    if(nsp != null) nsps.add(nsp);

                }catch(Exception e){}
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return nsps;
    }
}

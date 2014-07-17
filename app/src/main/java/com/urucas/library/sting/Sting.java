package com.urucas.library.sting;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by Urucas on 7/16/14.
 */
public class Sting {

    private Socket socket;

    public void emitNext() {
        socket.emit("next", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject)args[0];
                Log.i("vuelta", obj.toString());
            }
        });
    }

    public void emitPrev() {
        socket.emit("prev", new Emitter.Listener(){

            @Override
            public void call(Object... args) {
                Log.i("prev", "prev");
            }
        });
    }

    public Sting(String url) {

        try {
            socket = IO.socket(url);
            socket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


}

package com.urucas.library.sting;

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

    public Sting(String url) {

        try {
            socket = IO.socket(url);
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    /*
                    JSONObject note = new JSONObject();
                    try {
                        note.put("note","g");
                        note.put("octave","5");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    */
                    socket.emit("next", "");
                    socket.disconnect();
                }

            }).on("event", new Emitter.Listener() {

                @Override
                public void call(Object... args) {}

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                }

            });
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}

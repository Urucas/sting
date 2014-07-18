package com.urucas.sting.library;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by Urucas on 7/16/14.
 *
 * Rossscan! Rossscan! Rossscan! give me the tickets!
 *
 */

public class Sting {

    private Socket socket;

    public void emitNext() {
        socket.emit("next", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
            }
        });
    }

    public void emitPrev() {
        socket.emit("prev", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
            }
        });
    }

    public void emitUp(){
        socket.emit("up", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
            }
        });
    }

    public void emitDown(){
        socket.emit("down", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
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

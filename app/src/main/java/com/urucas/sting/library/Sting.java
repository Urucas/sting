package com.urucas.sting.library;

import android.util.Log;

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
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.i("connected", "yes");
                }
            });
            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.i("disconnected", "yes");
                }
            });
            socket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    Log.i("error", "aca");
                }
            });
            socket.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }



}

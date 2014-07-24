package com.urucas.sting.library;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.urucas.sting.callback.SocketConnectionCallback;

import java.net.URISyntaxException;

/**
 * Created by Urucas on 7/16/14.
 *
 * Rossscan! Rossscan! Rossscan! give me the tickets!
 *
 */

public class Sting {

    private Socket socket;

    public void emitRight() {
        socket.emit("right", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.i("emit ", args.toString());
            }
        });
    }

    public void emitLeft() {
        socket.emit("left", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                Log.i("emit ", args.toString());
            }
        });
    }

    public void emitUp(){
        socket.emit("up", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                Log.i("emit ", args.toString());
            }
        });
    }

    public void emitDown(){
        socket.emit("down", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
                Log.i("emit ", args.toString());
            }
        });
    }

    public Sting(String url, final SocketConnectionCallback callback) {
        try {

            socket = IO.socket(url);

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    callback.connected(args);
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

    public void reconnect(final SocketConnectionCallback callback) {
        if(socket != null) {
            socket.connect();
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                callback.connected(args);
                }
            });
        }
    }

    public void moveToFirst() {
        socket.emit("first", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
            }
        });
    }

    public void disconnect() {
        socket.disconnect();
    }
}

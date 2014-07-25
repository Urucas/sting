package com.urucas.sting.library;

import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.urucas.sting.callback.SocketConnectionCallback;

import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by Urucas on 7/16/14.
 *
 * Rossscan! Rossscan! Rossscan! give me the tickets!
 *
 */

public class Sting {

    private static final String TAG_NAME = "Sting class";
    private Socket socket;
    private HashMap<String, Socket> socketes;

    private static Sting _instance;

    public static Sting singleton() {
        if(_instance == null) {
            _instance = new Sting();
        }
        return _instance;
    }

    public void socket(String url, final SocketConnectionCallback callback) {

        try {

            if(socketes == null) {
                socketes = new HashMap<String, Socket>();
            }

            socket = socketes.get(url);

            for(String nsp : socketes.keySet()) {
                Log.i(TAG_NAME, "sockets nsp:" + nsp);
            }

            if(socket == null) {

                socket = IO.socket(url);
                socketes.put(url, socket);

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.i(TAG_NAME, "connected");
                        callback.connected(args);
                    }
                });

                socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.i(TAG_NAME, "disconnected");

                    }
                });
                socket.on(Socket.EVENT_ERROR, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Log.i(TAG_NAME, "error");
                    }
                });
                socket.connect();

            }else{
                Log.i(TAG_NAME, socket.toString());
                callback.connected();

                // socket.io().reconnection();
            }

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        // socket.disconnect();
    //    socket.close();
    }

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

    public void moveToFirst() {
        socket.emit("first", new Emitter.Listener(){
            @Override
            public void call(Object... args) {
            }
        });
    }

}

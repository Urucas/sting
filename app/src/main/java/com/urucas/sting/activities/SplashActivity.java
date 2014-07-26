package com.urucas.sting.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.urucas.sting.R;
import com.urucas.sting.application.StingApp;
import com.urucas.sting.controller.PersistentController;
import com.urucas.sting.library.Sting;
import com.urucas.sting.model.User;
import com.urucas.sting.utils.Utils;

/**
 * Created by Urucas on 7/23/14.
 */
public class SplashActivity extends Activity {

    private static final int LOGIN_INTENT = 1;
    private static final String TAG_NAME = "SplashSactivity";
    private Button joinBtt;
    private Button loginBtt;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        joinBtt = (Button) findViewById(R.id.joinBtt);
        joinBtt.setVisibility(View.INVISIBLE);
        joinBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Utils.openLink(SplashActivity.this, getResources().getString(R.string.joinURL));
            }
        });

        loginBtt= (Button) findViewById(R.id.loginBtt);
        loginBtt.setVisibility(View.INVISIBLE);
        loginBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_INTENT);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initApp();
            }
        }, 1500);
    }

    private void initApp(){

        StingApp app = StingApp.singleton();
        PersistentController persistance = app.getPersistance();
        try {
            User user = persistance.getUser(app.getApplicationContext());
            if(user != null) {
                initListActivity();
                return;
            }
        }catch(Exception e) { e.printStackTrace(); }
        showLoginButtons();
    }

    private void initListActivity(){
        Intent intent = new Intent(SplashActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showLoginButtons(){
        joinBtt.setVisibility(View.VISIBLE);
        loginBtt.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

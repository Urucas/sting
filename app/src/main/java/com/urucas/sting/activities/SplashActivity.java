package com.urucas.sting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.urucas.sting.R;
import com.urucas.sting.utils.Utils;

/**
 * Created by Urucas on 7/23/14.
 */
public class SplashActivity extends Activity {

    private static final int LOGIN_INTENT = 1;
    private Button joinBtt;
    private Button loginBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        joinBtt = (Button) findViewById(R.id.joinBtt);
        joinBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Utils.openLink(SplashActivity.this, getResources().getString(R.string.joinURL));
            }
        });

        loginBtt= (Button) findViewById(R.id.loginBtt);
        loginBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_INTENT);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });
    }
}

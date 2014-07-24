package com.urucas.sting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.urucas.library.sting.R;
import com.urucas.sting.utils.Utils;

/**
 * Created by Urucas on 7/23/14.
 */
public class LoginActivity extends Activity {

    private TextView forgotBtt;
    private Button loginBtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotBtt = (TextView) findViewById(R.id.forgotBtt);
        forgotBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.openLink(LoginActivity.this, getResources().getString(R.string.forgotURL));
            }
        });

        loginBtt = (Button) findViewById(R.id.loginBtt);
        loginBtt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginSuccess();
            }
        });
    }

    private void onLoginSuccess() {
        Intent intent = new Intent(LoginActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}

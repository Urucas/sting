package com.urucas.sting.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.urucas.sting.R;
import com.urucas.sting.model.CustomError;
import com.urucas.sting.utils.Utils;

/**
 * Created by Urucas on 7/23/14.
 */
public class LoginActivity extends Activity {

    private TextView forgotBtt;
    private Button loginBtt;
    private EditText emailText;
    private EditText passText;

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

            }
        });

        String email = Utils.getEmailAccount(LoginActivity.this);

        emailText = (EditText) findViewById(R.id.editText);
        emailText.setText(email);

        passText = (EditText) findViewById(R.id.passEdit);
    }

    private void login() {
        String email = emailText.getText().toString().trim();
        if(email.length()==0) {
            Utils.Toast(LoginActivity.this, R.string.emailempty, Toast.LENGTH_LONG);
            return;
        }

        String pass = passText.getText().toString().trim();
        if(email.length()==0) {
            Utils.Toast(LoginActivity.this, R.string.passempty, Toast.LENGTH_LONG);
            return;
        }

    }

    private void onLoginSuccess(){
        Intent intent = new Intent(LoginActivity.this, ListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void onLoginError(CustomError error){
        Utils.Toast(LoginActivity.this, error.getLocalizedMessage());
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}

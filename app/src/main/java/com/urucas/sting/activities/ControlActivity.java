package com.urucas.sting.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;


import com.urucas.library.sting.R;
import com.urucas.sting.callback.SocketConnectionCallback;
import com.urucas.sting.gestures.OnSwipeTouchListener;
import com.urucas.sting.library.Sting;
import com.urucas.sting.model.SlideNamespace;
import com.urucas.sting.utils.Utils;


public class ControlActivity extends ActionBarActivity {

    private ImageButton prevButton;
    private WebView preview;
    private ImageButton nextButton;
    private Sting sting;
    private ImageButton upButton;
    private ImageButton downButton;

    private boolean previewLoaded = false;
    private ProgressDialog dialog;

    public static SlideNamespace namespace;
    private static String baseURL = "http://sting.jit.su";
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        actionBar = getSupportActionBar();
        actionBar.setTitle(namespace.getDesc());

        String nsp = namespace.getNamespace();
        String url = nsp.replace("-","/");

        nextButton = (ImageButton) findViewById(R.id.nextButton);
        prevButton = (ImageButton) findViewById(R.id.prevButton);
        upButton = (ImageButton) findViewById(R.id.upButton);
        downButton = (ImageButton) findViewById(R.id.downButton);

        preview = (WebView) findViewById(R.id.previewWebView);
        preview.getSettings().setJavaScriptEnabled(true);
        preview.setWebViewClient(new BrowserClient());

        dialog = ProgressDialog.show(ControlActivity.this, "", "loading preview...", true);
        dialog.setCancelable(false);
        dialog.show();

        preview.loadUrl(baseURL + url);
    }

    @Override
    public void onBackPressed() {
        /*
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        */
    }

    private void prepareSocket() {

        dialog.cancel();
        dialog = ProgressDialog.show(ControlActivity.this, "", "connecting socket...", true);
        dialog.setCancelable(false);
        dialog.show();

        String nsp = namespace.getNamespace();
        sting = new Sting(baseURL + nsp, new SocketConnectionCallback() {
            @Override
            public void connected(Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        socketConnected();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_goback:
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void socketConnected() {
        dialog.cancel();
        prepareSlideControl();
    }

    private void prepareSlideControl() {

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitRight();
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitLeft();
            }
        });
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitUp();
            }
        });
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitDown();
            }
        });

        preview.setOnTouchListener(new OnSwipeTouchListener(ControlActivity.this) {
            public void onSwipeTop() {
                sting.emitDown();
            }
            public void onSwipeRight() {
                sting.emitLeft();
            }
            public void onSwipeLeft() {
                sting.emitRight();
            }
            public void onSwipeBottom() {
                sting.emitUp();
            }
        });
        sting.moveToFirst();

        Utils.Toast(ControlActivity.this, R.string.readytoslide);
    }

    private class BrowserClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url){
            super.onPageFinished(view, url);

            if(!previewLoaded) {
                previewLoaded = true;
                prepareSocket();
            }
        }
    }

}
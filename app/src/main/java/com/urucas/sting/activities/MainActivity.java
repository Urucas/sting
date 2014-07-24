package com.urucas.sting.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import android.view.View;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;


import com.urucas.library.sting.R;
import com.urucas.sting.gestures.OnSwipeTouchListener;
import com.urucas.sting.library.Sting;


public class MainActivity extends ActionBarActivity {

    private ImageButton prevButton;
    private WebView preview;
    private ImageButton nextButton;
    private Sting sting;
    private ImageButton upButton;
    private ImageButton downButton;

    private boolean previewLoaded = false;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://sting.jit.su/urucas-eztenapp";
        sting = new Sting(url);

        dialog = ProgressDialog.show(MainActivity.this, "", "loading preview...", true);
        dialog.setCancelable(false);
        dialog.show();

        nextButton = (ImageButton) findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitRight();
            }
        });

        prevButton = (ImageButton) findViewById(R.id.prevButton);
        prevButton.setVisibility(View.INVISIBLE);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitLeft();
            }
        });

        upButton = (ImageButton) findViewById(R.id.upButton);
        upButton.setVisibility(View.INVISIBLE);
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitUp();
            }
        });

        downButton = (ImageButton) findViewById(R.id.downButton);
        downButton.setVisibility(View.INVISIBLE);
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sting.emitDown();
            }
        });

        preview = (WebView) findViewById(R.id.previewWebView);
        preview.getSettings().setJavaScriptEnabled(true);
        preview.setWebViewClient(new BrowserClient());
        //preview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        preview.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
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

        preview.loadUrl("http://sting.jit.su/urucas/eztenapp/");
    }

    private void startSliiding() {

        nextButton.setVisibility(View.VISIBLE);
        prevButton.setVisibility(View.VISIBLE);
        upButton.setVisibility(View.VISIBLE);
        downButton.setVisibility(View.VISIBLE);

        sting.moveToFirst();

        dialog.cancel();
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
                startSliiding();
            }
        }
    }

}

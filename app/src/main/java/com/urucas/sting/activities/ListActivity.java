package com.urucas.sting.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.urucas.library.sting.R;
import com.urucas.sting.utils.Utils;

/**
 * Created by Urucas on 7/23/14.
 */
public class ListActivity extends ActionBarActivity {

    private TextView forgotBtt;
    private ActionBar actionBar;
    private ListView slideList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.myslides);

        slideList = (ListView) findViewById(R.id.slidesList);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

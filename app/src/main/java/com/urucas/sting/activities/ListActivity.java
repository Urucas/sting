package com.urucas.sting.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.urucas.library.sting.R;
import com.urucas.sting.adapters.SlideAdapter;
import com.urucas.sting.model.SlideNamespace;

import java.util.ArrayList;

/**
 * Created by Urucas on 7/23/14.
 */
public class ListActivity extends ActionBarActivity {

    private static final int CONTROL_INTENT = 1;
    private TextView forgotBtt;
    private ActionBar actionBar;
    private ListView slideList;
    private SlideAdapter nspAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.myslides);

        ArrayList<SlideNamespace> namespaces = new ArrayList<SlideNamespace>();
        namespaces.add(new SlideNamespace(1, "sting", "/urucas-sting", "Really short slide test"));
        namespaces.add(new SlideNamespace(2, "appium", "/urucas-appium", "Filmaj Appium presentation used as example"));
        namespaces.add(new SlideNamespace(3, "eztenapp", "/urucas-eztenapp", "So ? What is eztenapp ? A quick slide presentation"));

        nspAdapter = new SlideAdapter(ListActivity.this, R.layout.list_item, namespaces);

        slideList = (ListView) findViewById(R.id.slidesList);
        slideList.setAdapter(nspAdapter);
        slideList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SlideNamespace nsp = (SlideNamespace) parent.getItemAtPosition(position);
                openSlideControl(nsp);
             }
        });
    }

    private void openSlideControl(SlideNamespace nsp) {
        Intent intent = new Intent(ListActivity.this, ControlActivity.class);
        ControlActivity.namespace = nsp;
        startActivityForResult(intent, CONTROL_INTENT);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
    
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

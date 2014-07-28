package com.urucas.sting.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.urucas.sting.R;
import com.urucas.sting.adapters.SlideAdapter;
import com.urucas.sting.application.StingApp;
import com.urucas.sting.callback.SlidesNamespacesCallback;
import com.urucas.sting.model.CustomError;
import com.urucas.sting.model.SlideNamespace;
import com.urucas.sting.model.User;
import com.urucas.sting.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Urucas on 7/23/14.
 */
public class ListActivity extends ActionBarActivity {

    private static final int CONTROL_INTENT = 1;
    private static final String TAG_NAME = "ListActivity";
    private TextView forgotBtt;
    private ActionBar actionBar;
    private ListView slideList;
    private SlideAdapter nspAdapter;
    private User user;
    private ProgressDialog dialog;
    private StingApp app;

    private ArrayList<SlideNamespace> namespaces = new ArrayList<SlideNamespace>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.myslides);

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

        app = StingApp.singleton();
        try {
            user = app.getPersistance().getUser(ListActivity.this);
            getSlides(user);

        }catch(Exception e){
            app.logout(ListActivity.this);
        }
    }

    private void getSlides(User user){

        dialog = ProgressDialog.show(ListActivity.this, "", getResources().getString(R.string.gettingslides), true);
        dialog.setCancelable(true);
        dialog.show();

        app.getApiController().getSlides(user, new SlidesNamespacesCallback() {
            @Override
            public void onSuccess(ArrayList<SlideNamespace> namespaces) {
                onGetSlidesSuccess(namespaces);
            }

            @Override
            public void onError(CustomError error) {
                onGetSlidesError(error);
            }
        });
    }

    private void onGetSlidesSuccess(ArrayList<SlideNamespace> namespaces) {

        this.namespaces = namespaces;
        try {
            nspAdapter.clear();
            nspAdapter.addAll(namespaces);
            nspAdapter.notifyDataSetChanged();

            dialog.cancel();

        }catch(Exception e){}
    }

    private void onGetSlidesError(CustomError error) {
        dialog.cancel();
        Utils.Toast(ListActivity.this, error.getLocalizedMessage());
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

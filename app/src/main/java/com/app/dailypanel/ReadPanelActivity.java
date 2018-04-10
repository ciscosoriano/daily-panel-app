package com.app.dailypanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;

import com.app.dailypanel.helper.PanelFeedTask;

public class ReadPanelActivity extends AppCompatActivity {
    private static final String TAG = ReadPanelActivity.class.getName();
    //private static final String apiUrl = "http://dailypanel.org/feed/panels";
    private static final String apiUrl = "https://api.myjson.com/bins/14p8lr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_read_panel);

        Toolbar toolbar = findViewById(R.id.read_panel_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.top_story_text));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String articleId = bundle.getString("nodeId");

        final GridView gvPanel = findViewById(R.id.grid_view_panels);

        try {
            if (isNetworkConnectionAvailable()) {
                PanelFeedTask panelFeedTask = new PanelFeedTask(getApplicationContext(),
                        ReadPanelActivity.this, gvPanel, articleId, apiUrl);
                panelFeedTask.execute();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Unable to load panel feed")
                        .setMessage("Please check your Internet connection")
                        .setPositiveButton(getString(R.string.ok_prompt), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    protected boolean isNetworkConnectionAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
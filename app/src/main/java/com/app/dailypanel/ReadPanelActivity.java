package com.app.dailypanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String articleId = bundle.getString("nodeId");

        final GridView gvPanel = (GridView) findViewById(R.id.grid_view_panels);

        try {
            PanelFeedTask panelFeedTask = new PanelFeedTask(getApplicationContext(),
                    ReadPanelActivity.this, gvPanel, articleId, apiUrl);
            panelFeedTask.execute();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
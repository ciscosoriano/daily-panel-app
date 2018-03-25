package com.app.dailypanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.app.dailypanel.helper.ArticleFeedTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    //private static final String apiUrl = "http://dailypanel.org/feed/articles";
    private static final String apiUrl = "https://api.myjson.com/bins/sjkfj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        final GridView gvArticle = (GridView) findViewById(R.id.grid_view_articles);

        try {
            ArticleFeedTask articleFeedTask = new ArticleFeedTask(getApplicationContext(),
                    MainActivity.this, gvArticle, apiUrl);
            articleFeedTask.execute();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
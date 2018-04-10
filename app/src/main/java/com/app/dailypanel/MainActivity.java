package com.app.dailypanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.app.dailypanel.helper.ArticleFeedTask;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    //private static final String apiUrl = "http://dailypanel.org/feed/articles";
    private static final String apiUrl = "https://api.myjson.com/bins/sjkfj";

    private GridView mGvArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mGvArticle = findViewById(R.id.grid_view_articles);

        loadArticleFeed();

        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_container);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadArticleFeed();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    public void loadArticleFeed() {
        try {
            if (isNetworkConnectionAvailable()) {
                ArticleFeedTask articleFeedTask = new ArticleFeedTask(getApplicationContext(),
                        MainActivity.this, mGvArticle, apiUrl);
                articleFeedTask.execute();
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Unable to load articles")
                        .setMessage("Please check your Internet connection")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.action_about_us) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
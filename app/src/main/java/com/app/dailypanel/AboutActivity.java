package com.app.dailypanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends AppCompatActivity {
    private static final String TAG = AboutActivity.class.getName();

    private String htmlAsString;
    private int currentWebViewId;
    private int previousWebViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbar = findViewById(R.id.about_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.about_us_text));

        htmlAsString = getString(R.string.about_us_content_text);
        currentWebViewId = R.id.web_view_about_us;

        loadCurrentWebView();
    }

    public void tabsOnClick(View view) {
        int webViewIdOnClick = view.getId();

        switch (webViewIdOnClick) {
            case R.id.text_view_mission:
                htmlAsString = getString(R.string.mission_text);
                currentWebViewId = R.id.web_view_mission;
                break;

            case R.id.text_view_news:
                htmlAsString = getString(R.string.news_text);
                currentWebViewId = R.id.web_view_news;
                break;

            case R.id.text_view_founder:
                htmlAsString = getString(R.string.founder_text);
                currentWebViewId = R.id.web_view_founder;
                break;

            case R.id.text_view_advisers:
                htmlAsString = getString(R.string.advisers_text);
                currentWebViewId = R.id.web_view_advisers;
                break;

            case R.id.text_view_contact_us:
                htmlAsString = getString(R.string.contact_us_text);
                currentWebViewId = R.id.web_view_contact_us;
                break;

            default:
                htmlAsString = null;
                currentWebViewId = 0;
                Log.d(TAG, "Invalid link selection");
                break;
        }

        loadCurrentWebView();
    }

    public void loadCurrentWebView() {
        if((currentWebViewId != 0) && (htmlAsString != null)) {
            WebView currentWebView = findViewById(previousWebViewId);

            if(currentWebViewId != R.id.web_view_about_us) {
                currentWebView.setVisibility(View.GONE);
            }

            WebView newWebView = findViewById(currentWebViewId);
            newWebView.setVisibility(View.VISIBLE);

            WebSettings webSettings = newWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            newWebView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);
            /*newWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url)
                {
                    try {
                       view.loadUrl("javascript:(window.onload = function() { " +
                               "(head = document.getElementsByTagName('header')[0]); head.parentNode.removeChild(head); " +
                               "(menu = document.getElementsByClassName('menu-box')[0]); menu.parentNode.removeChild(menu); " +
                               "(footer_about_us = document.getElementsByTagName('footer_about_us')[0]); footer_about_us.parentNode.removeChild(footer_about_us); " +
                               "})()");
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                }
            });*/

            previousWebViewId = currentWebViewId;
        } else {
            Log.d(TAG, "Couldn't load WebView");
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
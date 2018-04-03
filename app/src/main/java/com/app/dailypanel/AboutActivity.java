package com.app.dailypanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class AboutActivity extends AppCompatActivity {

    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // get html content
        String htmlAsString = getString(R.string.aboutus_text);
        //use WebView to see it
        WebView webView = findViewById(R.id.aboutus_webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);
        webView.setVisibility(View.VISIBLE);
        x = R.id.aboutus_webview;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.daily_panel_home:
                //back to home screen
                Intent intent = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.daily_panel_about:

                //make current webview invisible
                WebView webView1 = findViewById(x);
                webView1.setVisibility(View.GONE);

                // About screen - get html content and make new webview visible
                String htmlAsStringAbout = getString(R.string.aboutus_text);
                WebView webViewAbout = findViewById(R.id.aboutus_webview);
                webViewAbout.setWebViewClient(new WebViewClient());
                webViewAbout.loadDataWithBaseURL(null, htmlAsStringAbout, "text/html", "utf-8", null);
                webViewAbout.setVisibility(View.VISIBLE);
                //save visible webview in variable
                x = R.id.aboutus_webview;
                break;

            case R.id.mission:

                //make current webview invisible
                WebView webView2 = findViewById(x);
                webView2.setVisibility(View.GONE);

                // Mission screen - get html content and make new webview visible
                String htmlAsStringMission = getString(R.string.mission_text);
                WebView webViewMission = findViewById(R.id.mission_webview);
                webViewMission.setWebViewClient(new WebViewClient());
                webViewMission.loadDataWithBaseURL(null, htmlAsStringMission, "text/html", "utf-8", null);
                webViewMission.setVisibility(View.VISIBLE);
                //save visible webview in variable
                x = R.id.mission_webview;
                break;

            case R.id.news:
                //make current webview invisible
                WebView webView3 = findViewById(x);
                webView3.setVisibility(View.GONE);

                // Mission screen - get html content and make new webview visible
                String htmlAsStringNews = getString(R.string.news_text);
                WebView webViewNews = findViewById(R.id.news_webview);
                webViewNews.setWebViewClient(new WebViewClient());
                webViewNews.loadDataWithBaseURL(null, htmlAsStringNews, "text/html", "utf-8", null);
                webViewNews.setVisibility(View.VISIBLE);
                //save visible webview in variable
                x = R.id.news_webview;
                break;

            case R.id.founder:
                //make current webview invisible
                WebView webView4 = findViewById(x);
                webView4.setVisibility(View.GONE);

                // Mission screen - get html content and make new webview visible
                String htmlAsStringFounder = getString(R.string.founder_text);
                WebView webViewFounder = findViewById(R.id.founder_webview);
                webViewFounder.setWebViewClient(new WebViewClient());
                webViewFounder.loadDataWithBaseURL(null, htmlAsStringFounder, "text/html", "utf-8", null);
                webViewFounder.setVisibility(View.VISIBLE);
                //save visible webview in variable
                x = R.id.founder_webview;
                break;

            case R.id.advisors:
                //make current webview invisible
                WebView webView5 = findViewById(x);
                webView5.setVisibility(View.GONE);

                // Advisors screen - get our html content and make new webview visible
                String htmlAsStringAdvisors = getString(R.string.advisors_text);
                WebView webViewAdvisors = findViewById(R.id.advisors_webview);
                webViewAdvisors.setVisibility(View.VISIBLE);
                webViewAdvisors.setWebViewClient(new WebViewClient());
                webViewAdvisors.loadDataWithBaseURL(null, htmlAsStringAdvisors, "text/html", "utf-8", null);
                //save visible webview in variable
                x = R.id.advisors_webview;
                break;

            case R.id.contactus:
                //make current webview invisible
                WebView webView6 = findViewById(x);
                webView6.setVisibility(View.GONE);

                // Contact Us screen - get html content and make new webview visible
                String htmlAsStringContactus = getString(R.string.contactus_text);
                WebView webViewContactus = findViewById(R.id.contactus_webview);
                webViewContactus.setWebViewClient(new WebViewClient());
                webViewContactus.loadDataWithBaseURL(null, htmlAsStringContactus, "text/html", "utf-8", null);
                webViewContactus.setVisibility(View.VISIBLE);
                //save visible webview in variable
                x = R.id.contactus_webview;
                break;

        }

    }


}


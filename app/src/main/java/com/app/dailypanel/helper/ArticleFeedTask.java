package com.app.dailypanel.helper;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.app.dailypanel.adapter.ArticlesFeedAdapter;
import com.app.dailypanel.data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleFeedTask extends AsyncTask<Void, Integer, Long> {
    private static final String TAG = ArticleFeedTask.class.getName();

    private Context mContext;
    private Activity mActivity;

    private String mApiUrl;

    private List<Article> mArticleList;

    private GridView mGvArticle;

    public ArticleFeedTask(Context newContext, Activity newActivity, GridView newGvArticle, String newApiUrl) {
        mContext = newContext;
        mActivity = newActivity;
        mGvArticle = newGvArticle;
        mApiUrl = newApiUrl;

        mArticleList = new ArrayList<>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Long doInBackground(Void... urls) {
        Log.i(TAG, "doInBackground");

        HttpHandler httpHandler = new HttpHandler();
        String responseFromConnectionRequest = httpHandler.makeServiceCall(mApiUrl);

        if (responseFromConnectionRequest != null) {
            try {
                JSONObject jsonObject = new JSONObject(responseFromConnectionRequest);

                JSONArray nodeArray = jsonObject.getJSONArray("nodes");

                for (int i = 0; i < nodeArray.length(); i++) {
                    JSONObject panel = nodeArray.getJSONObject(i);

                    JSONObject nodeObject = panel.getJSONObject("node");
                    String panelNodeId = nodeObject.getString("Nid");
                    String panelTitle = nodeObject.getString("title");
                    String panelPublishedDate = nodeObject.getString("Published");

                    String panelImageSrc;
                    JSONObject imageNodeObject = nodeObject.getJSONObject("Image");

                    if (imageNodeObject.length() != 0 && imageNodeObject.has("src")) {
                        panelImageSrc = imageNodeObject.optString("src");
                    } else {
                        panelImageSrc = "";
                    }

                    mArticleList.add(new Article(panelNodeId, panelTitle, panelImageSrc, panelPublishedDate));
                }
            } catch (final JSONException e) {
                Log.e(TAG, e.getMessage());
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,
                                e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        } else {
            Log.e(TAG, "API Request unsuccessful");
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext,
                            "API Request unsuccessful",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: articleList size = " + mArticleList.size());

        //Collections.sort(articles);

        ArticlesFeedAdapter articlesFeedAdapter = new ArticlesFeedAdapter(mContext, mActivity, mArticleList);
        mGvArticle.setAdapter(articlesFeedAdapter);
        articlesFeedAdapter.notifyDataSetChanged();
    }
}
package com.app.dailypanel.helper;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.app.dailypanel.adapter.PanelsFeedAdapter;
import com.app.dailypanel.data.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PanelFeedTask extends AsyncTask<Void, Integer, Long> {
    private static final String TAG = PanelFeedTask.class.getName();

    private Context mContext;
    private Activity mActivity;

    private String mArticleId;
    private String mApiUrl;

    private List<Article> mPanelList;

    private GridView mGvPanel;

    public PanelFeedTask(Context newContext, Activity newActivity, GridView newGvPanel, String newArticleId, String newApiUrl) {
        mContext = newContext;
        mActivity = newActivity;
        mGvPanel = newGvPanel;
        mArticleId = newArticleId;
        mApiUrl = newApiUrl;

        mPanelList = new ArrayList<>();
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
                    String panelArticleId = nodeObject.getString("Article");

                    Log.d(TAG, "ArticleId: " + mArticleId + " PanelArticleId: " + panelArticleId);

                    if (panelArticleId.equalsIgnoreCase(mArticleId)) {
                        String panelCaption = nodeObject.getString("Caption");

                        String panelImageSrc;
                        JSONObject imageNodeObject = nodeObject.getJSONObject("Image");

                        if (imageNodeObject.length() != 0 && imageNodeObject.has("src")) {
                            panelImageSrc = imageNodeObject.optString("src");
                        } else {
                            panelImageSrc = "";
                        }

                        mPanelList.add(new Article(panelArticleId, panelCaption, panelImageSrc));
                    }
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
        Log.d(TAG, "onPostExecute: panelList size = " + mPanelList.size());

        //Collections.sort(articles);

        PanelsFeedAdapter panelsFeedAdapter = new PanelsFeedAdapter(mContext, mActivity, mPanelList);
        mGvPanel.setAdapter(panelsFeedAdapter);
        panelsFeedAdapter.notifyDataSetChanged();
    }
}
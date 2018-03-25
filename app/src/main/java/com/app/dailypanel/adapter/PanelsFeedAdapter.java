package com.app.dailypanel.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dailypanel.R;
import com.app.dailypanel.data.Article;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class PanelsFeedAdapter extends BaseAdapter {
    private static final String TAG = PanelsFeedAdapter.class.getName();

    private Context mContext;
    private Activity mActivity;
    private List<Article> mPanelList;

    private ImageLoader mImageLoader;
    private DisplayImageOptions displayImageOptions;

    public PanelsFeedAdapter(Context newContext, Activity newActivity, List<Article> newPanelList) {
        Log.i(TAG, "PanelsFeedAdapter");

        mContext = newContext;
        mActivity = newActivity;
        mPanelList = newPanelList;

        mImageLoader = ImageLoader.getInstance();

        displayImageOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .postProcessor(null)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(200))
                .build();
    }

    @Override
    public int getCount() {
        return mPanelList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Article article = mPanelList.get(position);

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.thumbnail_panel_feed, null);
        }

        ImageView ivPanelImage = (ImageView) view.findViewById(R.id.image_view_panel_thumbnail);
        TextView tvPanelCaption = (TextView) view.findViewById(R.id.text_view_panel_caption);

        String panelImageUri = article.getImageResource();
        String panelCaption = article.getCaption();

        Log.e(TAG, panelCaption);

        mImageLoader.displayImage(panelImageUri, ivPanelImage, displayImageOptions);
        tvPanelCaption.setText(panelCaption);

        return view;
    }
}
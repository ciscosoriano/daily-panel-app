package com.app.dailypanel.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.dailypanel.R;
import com.app.dailypanel.ReadPanelActivity;
import com.app.dailypanel.data.Article;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

public class ArticlesFeedAdapter extends BaseAdapter {
    private static final String TAG = ArticlesFeedAdapter.class.getName();

    private Context mContext;
    private Activity mActivity;
    private List<Article> mArticleList;

    private ImageLoader mImageLoader;
    private DisplayImageOptions displayImageOptions;

    public ArticlesFeedAdapter(Context newContext, Activity newActivity, List<Article> newArticleList) {
        Log.i(TAG, "ArticlesFeedAdapter");

        mContext = newContext;
        mActivity = newActivity;
        mArticleList = newArticleList;

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(mContext));
        mImageLoader = ImageLoader.getInstance();

        displayImageOptions = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.empty_background)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .postProcessor(null)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(200))
                .build();

        displayHeadline();
    }

    @Override
    public int getCount() {
        return mArticleList.size();
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
    public View getView(final int position, View view, ViewGroup parent) {
        Article article = mArticleList.get(position);

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.thumbnail_article_feed, null);
        }

        ImageView ivArticleThumbnail = (ImageView) view.findViewById(R.id.image_view_article_thumbnail);
        TextView tvArticleDescription = (TextView) view.findViewById(R.id.text_view_article_description);

        final String articleId = article.getNodeId();
        String articleImageUri = article.getImageResource();
        String articleDescription = article.getDescription();

        mImageLoader.displayImage(articleImageUri, ivArticleThumbnail, displayImageOptions);
        tvArticleDescription.setText(articleDescription);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nodeId", articleId);

                Intent intent = new Intent(mContext, ReadPanelActivity.class);
                intent.putExtras(bundle);

                mContext.startActivity(intent);
            }
        });


        return view;
    }

    public View displayHeadline() {
        LayoutInflater layoutInflater = LayoutInflater.from(mActivity);
        View view = layoutInflater.inflate(R.layout.activity_main, null);

        ImageView ivHeadline = (ImageView) mActivity.findViewById(R.id.image_view_headline_image);
        TextView tvHeadlineDescription = (TextView) mActivity.findViewById(R.id.text_view_headline_description);

        final String headlineId = mArticleList.get(0).getNodeId();
        String headlineDescription = mArticleList.get(0).getDescription();
        String headlineImageUri = mArticleList.get(0).getImageResource();

        tvHeadlineDescription.setText(headlineDescription);
        mImageLoader.displayImage(headlineImageUri, ivHeadline, displayImageOptions);

        ivHeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("nodeId", headlineId);

                Intent intent = new Intent(mContext, ReadPanelActivity.class);
                intent.putExtras(bundle);

                mContext.startActivity(intent);
            }
        });

        mArticleList.remove(0);

        return view;
    }
}
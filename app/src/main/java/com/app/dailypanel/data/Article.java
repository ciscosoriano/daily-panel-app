package com.app.dailypanel.data;

public class Article implements Comparable<Article> {
    private String mNodeId;
    private String mDescription;
    private String mCaption;
    private String mImageResource;
    private String mPublishedDate;

    public Article(String newNodeId, String newDescription, String newImageResource, String newPublishedDate) {
        mNodeId = newNodeId;
        mDescription = newDescription;
        mImageResource = newImageResource;
        mPublishedDate = newPublishedDate;
    }

    public Article(String newNodeId, String newCaption, String newImageResource) {
        mNodeId = newNodeId;
        mCaption = newCaption;
        mImageResource = newImageResource;
    }

    public String getNodeId() {
        return mNodeId;
    }

    public void setNodeId(String newNodId) {
        mNodeId = newNodId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String newDescription) {
        mDescription = newDescription;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String newCaption) {
        mCaption = newCaption;
    }

    public String getImageResource() {
        return mImageResource;
    }

    public void setImageResource(String newImageResource) {
        mImageResource = newImageResource;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String newPublishedDate) {
        mPublishedDate = newPublishedDate;
    }

    @Override
    public int compareTo(Article article) {
        return getPublishedDate().compareTo(article.getPublishedDate());
    }
}
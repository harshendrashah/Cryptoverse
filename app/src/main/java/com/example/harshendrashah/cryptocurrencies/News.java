package com.example.harshendrashah.cryptocurrencies;

public class News {

    String newsId;
    String newsTitle;
    String newsSource;
    String newsImageURL;
    String newsCategory;
    String newsSrcImg;
    String newsBody;
    Long newsPublishedOn;
    String newsUrl;

    public News(String newsId,String newsTitle, String newsSource, String newsImageURL, String newsCategory,
                String newsSrcImg, String newsBody, Long newsPublishedOn, String newsUrl) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsSource = newsSource;
        this.newsImageURL = newsImageURL;
        this.newsCategory = newsCategory;
        this.newsSrcImg = newsSrcImg;
        this.newsBody = newsBody;
        this.newsPublishedOn = newsPublishedOn;
        this.newsUrl = newsUrl;
    }

    public News() {

    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsSource() {
        return newsSource;
    }

    public void setNewsSource(String newsSource) {
        this.newsSource = newsSource;
    }

    public String getNewsImageURL() {
        return newsImageURL;
    }

    public void setNewsImageURL(String newsImageURL) {
        this.newsImageURL = newsImageURL;
    }

    public String getNewsCategory() {
        return newsCategory;
    }

    public void setNewsCategory(String newsCategory) {
        this.newsCategory = newsCategory;
    }

    public String getNewsSrcImg() {
        return newsSrcImg;
    }

    public void setNewsSrcImg(String newsSrcImg) {
        this.newsSrcImg = newsSrcImg;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }

    public Long getNewsPublishedOn() {
        return newsPublishedOn;
    }

    public void setNewsPublishedOn(Long newsPublishedOn) {
        this.newsPublishedOn = newsPublishedOn;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}

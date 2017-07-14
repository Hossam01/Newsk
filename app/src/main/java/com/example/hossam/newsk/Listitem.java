package com.example.hossam.newsk;

import java.io.Serializable;

/**
 * Created by Hossam on 5/7/2017.
 */

public class Listitem implements Serializable {
    String author, title, description, urlToImage, source, publishedAt, url;
    int id;

    public Listitem() {
    }

    public Listitem(String author, String description, String publishedAt, String source, String title, String urlToImage, String url, int id) {
        this.author = author;
        this.description = description;
        this.publishedAt = publishedAt;
        this.source = source;
        this.title = title;
        this.urlToImage = urlToImage;
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

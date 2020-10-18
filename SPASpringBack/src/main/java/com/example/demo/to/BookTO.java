package com.example.demo.to;

import java.util.Date;

public class BookTO {
    private Integer id;
    
    private String title;
    private Integer pages;
    private Date publishDate;
    private String edition;
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BookTO() {
    }

    public BookTO(Integer id, String title, Integer pages, Date publishDate, String edition, String type) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.publishDate = publishDate;
        this.edition = edition;
        this.type = type;
    }
}

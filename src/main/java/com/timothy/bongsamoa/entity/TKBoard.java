package com.timothy.bongsamoa.entity;

import java.util.Date;

public class TKBoard {
    private final int index;
    private String title;
    private String content;
    private final String writer;
    private final Date creationDate;
    private Date modifiedDate;

    public TKBoard(int index, String writer) {
        this.index = index;
        this.title = "Untitled";
        this.content = "";
        this.writer = writer;
        this.creationDate = new Date();
        this.modifiedDate = this.creationDate;
    }

    public int getIndex() {
        return this.index;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
            this.modifiedDate = new Date();
        }
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        if (content != null && !content.isEmpty()) {
            this.content = content;
            this.modifiedDate = new Date();
        }
    }

    public String getWriter() {
        return this.writer;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Date getModifiedDate() {
        return this.modifiedDate;
    }
}

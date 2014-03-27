package com.macs.starter.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Maksim_Alipov.
 */
public class Note implements Comparable<Note> {
    private String content;
    private Date createdDate;
    private String id;


    public Note() {
    }

    public Note(String content) {
        this.content = content;
        this.createdDate = new Date();
        this.id = UUID.randomUUID().toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int compareTo(Note o) {
        if (o == null) {
            return 1;
        }
        return createdDate.compareTo(o.createdDate);
    }
}

package com.macs.starter.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Session Token
 */
public class SessionToken {
    private String id;
    private String username;
    private Date creationDate;
    private long validTillTimestamp;

    public SessionToken(String username) {
        if (username == null) {
            throw new IllegalArgumentException("invalid username");
        }
        this.id = UUID.randomUUID().toString();
        this.creationDate = new Date();
        this.username = username;
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.creationDate);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        this.validTillTimestamp = cal.getTime().getTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getValidTillTimestamp() {
        return validTillTimestamp;
    }

    public void setValidTillTimestamp(long validTillTimestamp) {
        this.validTillTimestamp = validTillTimestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

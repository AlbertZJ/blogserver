package org.sang.bean;

import java.sql.Timestamp;

public class NoticeUser {
    private Long id;
    private String message;
    private Timestamp publishDate;
    private String nickname;
    private Long state;
    private String dename;
    private Long did;
    private Timestamp deleteTime;
    private Long uid;

    public String getDename() {
        return dename;
    }

    public void setDename(String dename) {
        this.dename = dename;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}

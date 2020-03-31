package org.sang.bean;

import java.sql.Timestamp;

/**
 * Created by albert on 2019/12/19.
 */
public class ComLikes {

    public Long countcomlikes;
    private Long id;
    private int likes;
    private Long uid;
    private Long cid;
    private Timestamp publishDate;
    private Long num;

    public Long getCountcomlikes() {
        return countcomlikes;
    }

    public void setCountcomlikes(Long countcomlikes) {
        this.countcomlikes = countcomlikes;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

}

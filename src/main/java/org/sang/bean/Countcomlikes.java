package org.sang.bean;

import java.sql.Timestamp;

public class Countcomlikes {
    private Long id;
    private Long countlike;
    private Long countdislike;
    private Long cid;
    private Timestamp publishDate;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    private int likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountlike() {
        return countlike;
    }

    public void setCountlike(Long countlike) {
        this.countlike = countlike;
    }

    public Long getCountdislike() {
        return countdislike;
    }

    public void setCountdislike(Long countdislike) {
        this.countdislike = countdislike;
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

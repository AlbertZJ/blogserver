package org.sang.bean;

import java.sql.Timestamp;

/**
 * Created by albert on 2019/12/19.
 */
public class Countlikes {

    private Long id;
    private Long countlike;
    private Long countdislike;
    private Long aid;
    private Timestamp publishDate;

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

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

}

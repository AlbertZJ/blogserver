package org.sang.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by albert on 2019/12/19.
 */
public class Comment {

    private Long id;
    private Long aid;
    private String content;
    private Timestamp publishDate;
    private Long parentId;
    private Long uid;
    private Long state;
    private Timestamp comDate;
    private String comname;
    private String userface;
    private Long countdislike;
    private Long countlike;
    private String nickname;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCountdislike() {
        return countdislike;
    }

    public void setCountdislike(Long countdislike) {
        this.countdislike = countdislike;
    }

    public Long getCountlike() {
        return countlike;
    }

    public void setCountlike(Long countlike) {
        this.countlike = countlike;
    }

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public Timestamp getComDate() {
        return comDate;
    }

    public void setComDate(Timestamp comDate) {
        this.comDate = comDate;
    }

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

}

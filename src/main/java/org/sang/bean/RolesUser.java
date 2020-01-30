package org.sang.bean;

public class RolesUser {
    private  int id;
    private int rid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private int uid;
    public RolesUser(){

    }
    public RolesUser(int id,int rid,int uid){
        this.id=id;
        this.rid=rid;
        this.uid=uid;
    }
}

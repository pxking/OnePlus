package com.example.demo.entity;

public class Address extends BaseEntity {
    private Integer id;

    private Integer uid;

    private String recvname;

    private String recvprovince;

    private String recvcity;

    private String recvarea;

    private String recvdistrict;

    private String recvaddress;

    private String recvphone;

    private String recvtel;

    private String recvzip;

    private String recvtag;

    private Integer isdefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getRecvname() {
        return recvname;
    }

    public void setRecvname(String recvname) {
        this.recvname = recvname == null ? null : recvname.trim();
    }

    public String getRecvprovince() {
        return recvprovince;
    }

    public void setRecvprovince(String recvprovince) {
        this.recvprovince = recvprovince == null ? null : recvprovince.trim();
    }

    public String getRecvcity() {
        return recvcity;
    }

    public void setRecvcity(String recvcity) {
        this.recvcity = recvcity == null ? null : recvcity.trim();
    }

    public String getRecvarea() {
        return recvarea;
    }

    public void setRecvarea(String recvarea) {
        this.recvarea = recvarea == null ? null : recvarea.trim();
    }

    public String getRecvdistrict() {
        return recvdistrict;
    }

    public void setRecvdistrict(String recvdistrict) {
        this.recvdistrict = recvdistrict == null ? null : recvdistrict.trim();
    }

    public String getRecvaddress() {
        return recvaddress;
    }

    public void setRecvaddress(String recvaddress) {
        this.recvaddress = recvaddress == null ? null : recvaddress.trim();
    }

    public String getRecvphone() {
        return recvphone;
    }

    public void setRecvphone(String recvphone) {
        this.recvphone = recvphone == null ? null : recvphone.trim();
    }

    public String getRecvtel() {
        return recvtel;
    }

    public void setRecvtel(String recvtel) {
        this.recvtel = recvtel == null ? null : recvtel.trim();
    }

    public String getRecvzip() {
        return recvzip;
    }

    public void setRecvzip(String recvzip) {
        this.recvzip = recvzip == null ? null : recvzip.trim();
    }

    public String getRecvtag() {
        return recvtag;
    }

    public void setRecvtag(String recvtag) {
        this.recvtag = recvtag == null ? null : recvtag.trim();
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}
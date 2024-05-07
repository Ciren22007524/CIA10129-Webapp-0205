package com.ren.administrator.dto;

import java.sql.Timestamp;

public class LoginState {

    // 管理員登入狀態DTO，用於確認登入狀態
    // 將Service查詢單項資料封包進這個DTO內
    private Integer admNo;

    private String admName;

    private Integer titleNo;

    private Byte admLogin;

    private Byte admLogout;

    private Timestamp admActiveTime;

    public LoginState() {
    }

    public LoginState(Integer admNo) {
        this.admNo = admNo;
    }

    public LoginState(Integer admNo, String admName, Integer titleNo, Byte admLogin, Byte admLogout, Timestamp admActiveTime) {
        this.admNo = admNo;
        this.admName = admName;
        this.titleNo = titleNo;
        this.admLogin = admLogin;
        this.admLogout = admLogout;
        this.admActiveTime = admActiveTime;
    }

    public Integer getAdmNo() {
        return admNo;
    }

    public void setAdmNo(Integer admNo) {
        this.admNo = admNo;
    }

    public String getAdmName() {
        return admName;
    }

    public void setAdmName(String admName) {
        this.admName = admName;
    }

    public Integer getTitleNo() {
        return titleNo;
    }

    public void setTitleNo(Integer titleNo) {
        this.titleNo = titleNo;
    }

    public Byte getAdmLogin() {
        return admLogin;
    }

    public void setAdmLogin(Byte admLogin) {
        this.admLogin = admLogin;
    }

    public Byte getAdmLogout() {
        return admLogout;
    }

    public void setAdmLogout(Byte admLogout) {
        this.admLogout = admLogout;
    }

    public Timestamp getAdmActiveTime() {
        return admActiveTime;
    }

    public void setAdmActiveTime(Timestamp admActiveTime) {
        this.admActiveTime = admActiveTime;
    }

}

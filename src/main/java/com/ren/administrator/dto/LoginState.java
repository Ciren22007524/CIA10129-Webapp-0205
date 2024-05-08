package com.ren.administrator.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class LoginState implements Serializable {

    // 管理員登入狀態DTO，用於確認登入狀態
    // 將Service查詢單項資料封包進這個DTO內
    private String jsessionid;

    private Byte admLogin;

    private Byte admLogout;

    private Timestamp admActiveTime;

    public LoginState() {
    }

    public LoginState(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public LoginState(Byte admLogin, Byte admLogout, Timestamp admActiveTime) {
        this.admLogin = admLogin;
        this.admLogout = admLogout;
        this.admActiveTime = admActiveTime;
    }

    public LoginState(String jsessionid, Byte admLogin, Byte admLogout, Timestamp admActiveTime) {
        this.jsessionid = jsessionid;
        this.admLogin = admLogin;
        this.admLogout = admLogout;
        this.admActiveTime = admActiveTime;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
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

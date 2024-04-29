package com.ren.administrator.service;

import com.Entity.Administrator;
import com.Entity.ServicePicture;

import java.sql.Date;
import java.util.List;

public interface AdministratorService_interface {
    // 新增(將前端request值放入VO由DAO執行SQL語法，並返回VO由Controller轉給View)
    // 不包括圖片
    Administrator addAdministrator(Administrator administrator);
    // 查詢單筆資料
    Administrator getOneAdministrator(Integer admNo);
    // 查詢所有資料
    List<Administrator> getAll();
    // 修改(返回值由Controller轉給View)
    Administrator updateAdministrator(Administrator administrator);
    // 刪除
    void deleteAdministrator(Integer admNo);
    // 上傳圖片
//    public void uploadPhoto(Integer admNo, byte[] admPhoto);
//    // 顯示大頭貼
//    public String photoSticker(Integer admNo);
//    // 修改圖片
//    public void ChangePhoto(Integer admNo, byte[] admPhoto);
//    // 註冊(含驗證)
//    public List<String> register(Administrator administrator);

}

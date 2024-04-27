package com.ren.title.service;

import com.Entity.ServicePicture;
import com.Entity.ServiceRobot;
import com.Entity.Title;
import com.Entity.TitleAdmVO;

import java.util.List;

public interface TitleService_interface {
    // 新增(將前端request值放入VO由DAO執行SQL語法，並返回VO由Controller轉給View)
    public Title addTitle(Title title);
    // 查詢單筆資料
    public Title getOneTitle(Integer titleNo);
    // 查詢所有資料
    public List<Title> getAll();
    // 查詢同職位編號的所有管理員
    public List<TitleAdmVO> getAdms(Integer titleNo);
    // 查詢同職位的所有管理員
    public List<TitleAdmVO> getAdms(String titleName);
    // 查詢所有管理員
    public List<TitleAdmVO> getAdmsAll();
    // 修改(將前端request值放入VO由DAO執行SQL語法，返回VO由Controller轉給View)
    public Title updateTitle(Title title);
    // 刪除
    public void deleteTitle(Integer titleNo);

}

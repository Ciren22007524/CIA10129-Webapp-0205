package com.ren.title.service;

import com.Entity.ServicePicture;
import com.Entity.ServiceRobot;
import com.ren.title.dao.TitleDAO_interface;
import com.ren.title.dao.TitleJDBCDAOImpl;

import java.util.List;

public class TitleServiceImpl implements TitleService_interface {

    private TitleDAO_interface dao;
    // DI
    public TitleServiceImpl() {
        dao = new TitleJDBCDAOImpl();
    }

    @Override
    public ServicePicture.TitleVO addTitle(String titleName) {
        ServicePicture.TitleVO titleVO = new ServicePicture.TitleVO();
        // 將傳入參數放入VO
        titleVO.setTitleName(titleName);
        // 將VO放入dao定義的方法內，使其執行資料庫操作
        dao.insert(titleVO);
        // 返回值作為呈現在View上使用
        return titleVO;
    }

    @Override
    public ServicePicture.TitleVO getOneTitle(Integer titleNo) {
        return dao.findByPrimaryKey(titleNo);
    }

    @Override
    public List<ServicePicture.TitleVO> getAll() {
        return dao.getAll();
    }

    @Override
    public List<ServiceRobot.TitleAdmVO> getAdms(Integer titleNo) {
        return dao.getAdms(titleNo);
    }

    @Override
    public List<ServiceRobot.TitleAdmVO> getAdms(String titleName) {
        return dao.getAdms(titleName);
    }

    @Override
    public List<ServiceRobot.TitleAdmVO> getAdmsAll() {
        return null;
    }

    @Override
    public ServicePicture.TitleVO updateTitle(Integer titleNo, String titleName) {
        ServicePicture.TitleVO titleVO = new ServicePicture.TitleVO();
        // 將傳入參數放入VO
        titleVO.setTitleNo(titleNo);
        titleVO.setTitleName(titleName);
        // 將VO放入dao定義的方法內，使其執行資料庫操作
        dao.update(titleVO);
        // 返回值作為呈現在View上使用
        return dao.findByPrimaryKey(titleNo);
    }

    @Override
    public void deleteTitle(Integer titleNo) {
        dao.delete(titleNo);
    }

}

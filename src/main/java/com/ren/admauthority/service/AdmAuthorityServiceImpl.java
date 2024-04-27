package com.ren.admauthority.service;

import com.Entity.AdmAuthority;
import com.Entity.News;
import com.ren.admauthority.dao.AdmAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdmAuthorityServiceImpl implements AdmAuthorityService_interface {

    @Autowired
    private AdmAuthorityRepository admAuthorityRepository;

    @Override
    public AdmAuthority addAdmAuthority(AdmAuthority admAuthority) {
        return admAuthorityRepository.save(admAuthority);
    }

    @Override
    public AdmAuthority getOneAdmAuthority(Integer titleNo) {
        return admAuthorityRepository.findById(titleNo);
    }

    @Override
    public List<AdmAuthority> getAll() {
        return dao.getAll();
    }

    @Override
    public AdmAuthority updateAdmAuthority(Integer titleNo, Integer authFuncNo) {
        AdmAuthority admAuthorityVO = new AdmAuthority();
        // 將傳入參數放入VO
        admAuthorityVO.setTitleNo(titleNo);
        admAuthorityVO.setAuthFuncNo(authFuncNo);
        // 將VO放入dao定義的方法內，使其執行資料庫操作
        dao.update(admAuthorityVO);
        // 返回值作為呈現在View上使用
        return admAuthorityVO;
    }

    @Override
    public void deleteAdmAuthority(Integer titleNo) {
        dao.delete(titleNo);
    }

}

package com.ren.title.service;

import com.Entity.ServicePicture;
import com.Entity.ServiceRobot;
import com.Entity.Title;
import com.Entity.TitleAdmVO;
import com.ren.title.dao.TitleDAO_interface;
import com.ren.title.dao.TitleJDBCDAOImpl;
import com.ren.title.dao.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("titleSvc")
public class TitleServiceImpl implements TitleService_interface {

    @Autowired
    private TitleRepository titleRepository;

    @Override
    public Title addTitle(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public Title getOneTitle(Integer titleNo) {
        return titleRepository.findById(titleNo).orElse(null);
    }

    @Override
    public List<Title> getAll() {
        return titleRepository.findAll();
    }

    @Override
    public List<TitleAdmVO> getAdms(Integer titleNo) {
        return null;
    }

    @Override
    public List<TitleAdmVO> getAdms(String titleName) {
        return null;
    }

    @Override
    public List<TitleAdmVO> getAdmsAll() {
        return null;
    }

    @Override
    public Title updateTitle(Title title) {
        return titleRepository.save(title);
    }

    @Override
    public void deleteTitle(Integer titleNo) {
        titleRepository.deleteById(titleNo);
    }

}

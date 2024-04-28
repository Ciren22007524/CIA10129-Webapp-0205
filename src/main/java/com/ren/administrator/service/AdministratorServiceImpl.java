package com.ren.administrator.service;

import com.Entity.Administrator;
import com.ren.administrator.dao.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService_interface {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Administrator addAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator getOneAdministrator(Integer admNo) {
        return administratorRepository.findById(admNo).orElse(null);
    }

    @Override
    public List<Administrator> getAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator updateAdministrator(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public void deleteAdministrator(Integer admNo) {
        administratorRepository.deleteById(admNo);
    }

//    @Override
//    public void uploadPhoto(Integer admNo,byte[] admPhoto) {
//        administratorRepository.upload(admNo, admPhoto);
//    }
//
//    @Override
//    public String photoSticker(Integer admNo) {
//        // 將byte[]陣列(二進制資料)轉成Base64(字串)傳到前端的src屬性即可轉成圖片顯示
//        byte[] admPhoto = administratorRepository.photoSticker(admNo);
//        String photoBase64 = null;
//        if (admPhoto != null) {
//            Base64.Encoder encoder = Base64.getEncoder();
//            photoBase64 = encoder.encodeToString(admPhoto);
//        } else {
//            photoBase64 = ""; // 或其他預設值
//        }
//        return photoBase64;
//    }
//
//    @Override
//    public void ChangePhoto(Integer admNo, byte[] admPhoto) {
//        administratorRepository.ChangePhoto(admNo, admPhoto);
//    }
//
//    @Override
//    public List<String> register(Administrator administrator) {
//        // 放驗證錯誤訊息，在controller迭代放入errorMessage
//        List<String> existData = new LinkedList<>();
//        // 輸入名字
//        String inputName = administrator.getAdmName();
//        // 輸入信箱
//        String inputEmail = administrator.getAdmEmail();
//        // 查詢使用者名稱與信箱，檢查是否有重複
//        existData = administratorRepository.findExistData(inputName, inputEmail);
//        if (existData.size() >= 1) {
//            return existData;
//        }
//
//        return existData;
//    }

}


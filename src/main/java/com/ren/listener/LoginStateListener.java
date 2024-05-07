package com.ren.listener;

import com.Entity.Administrator;
import com.ren.administrator.service.AdministratorServiceImpl;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class LoginStateListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
        HttpSession session = se.getSession();
        session.getServletContext();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 確認是否為登出狀態
        HttpSession session = se.getSession();
        Administrator administrator = (Administrator) session.getAttribute("administrator");
        AdministratorServiceImpl administratorSvc = new AdministratorServiceImpl();
        Byte loginState = administrator.getAdmLogin();
        Byte logoutState = administrator.getAdmLogout();
        if (loginState != 0 && logoutState != 1) {
            administrator.setAdmLogin(Byte.valueOf("0"));
            administrator.setAdmLogout(Byte.valueOf("1"));
            administratorSvc.updateAdministrator(administrator);
        }
    }
}
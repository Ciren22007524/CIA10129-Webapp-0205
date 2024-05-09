package com.ren.util;

import java.util.regex.Pattern;

public interface Constants {

    int PAGE_MAX_RESULT = 10;
    int FIRST = 0;
    int SUCCESS = 1;
    int FAILURE = 0;
    byte YES = 1;
    byte NO = 0;
    int FIRST_ORDER = 1;
    int SECOND_ORDER = 2;


    /**
     * 首頁路徑
     */
    public static final String backendIndex = "/backend/index";

    /**
     * 登入頁面路徑
     */
    public static final String loginPage = "/backend/login";

    /**
     * 註冊頁面路徑
     */
    public static final String registerPage = "/backend/register";

    /**
     * 忘記密碼頁面路徑
     */
    public static final String forgotPasswordPage = "/backend/forgotPassword";

}

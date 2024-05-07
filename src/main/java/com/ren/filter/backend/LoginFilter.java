package com.ren.filter.backend;

import com.Entity.Administrator;
import com.ren.administrator.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static com.ren.util.Constants.YES;

/**
 * 用於確認後台員工登入狀態的過濾器，
 * 主要有三功能:
 * 1.確認Session，防止重複登入
 * 2.自動登入功能
 * 3.權限驗證
 */
@WebFilter("/backend/*")
@Component
public class LoginFilter implements Filter {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        String requestURI = ((HttpServletRequest) req).getRequestURI();
        String resourceURI = ((HttpServletRequest) req).getServletPath();

        if (checkURL(requestURI)) {
            System.out.println("3");
        } else {
            // 因登入會將administrator物件放入Session，如果沒有，則代表還沒登入，將導向登入畫面
            // 補充:若Session不存在時也會得到null(第一次造訪網頁)，並導向登入頁面
            Administrator administrator = null;
            administrator = (Administrator) ((HttpServletRequest) req).getSession().getAttribute("administrator");
            if (administrator == null) {
                ((HttpServletResponse) res).sendRedirect(loginPage);
                return;
            }

            // 搜尋使用者cookie確認是否有管理員相關資訊
            Optional<Cookie> userCookie = Optional.ofNullable(((HttpServletRequest) req)
                            .getCookies())
                    .flatMap(this::userCookie);

            // 如果沒有cookie則回到登入頁面
            if(!userCookie.isPresent()) {
                ((HttpServletResponse) res).sendRedirect(loginPage);
                return;
            }

            // 前端輸入用戶名或Email
            String userId = req.getParameter("userId");
            // 確認是否為信箱格式，true則透過信箱搜尋使用者資訊，false則透過管理員編號搜尋使用者資訊
            if (validateEmail(userId)) {
                administrator = administratorSvc.getOneAdministrator(userId);
            } else {
                administrator = administratorSvc.getOneAdministrator(Integer.valueOf(userId));
            }

            Cookie cookie = null;
            // 確認使用者是否要自動登入
            String autoLogin = req.getParameter("autoLogin");
            // 如果使用者選擇自動登入，將管理員編號與管理員職位放入Cookie
            if (autoLogin.equals(YES) ) {
                String cookieName = administrator.getAdmNo().toString();
                String cookieValue = administrator.getTitle().getTitleNo().toString();
                cookie = new Cookie(cookieName, cookieValue);
                cookie.setMaxAge(864000);
                ((HttpServletResponse) res).addCookie(cookie);
            }
            System.out.println("1");
        }

        // 已登入，允許訪問
        if (resourceURI.endsWith(".css") || resourceURI.endsWith(".js")) {
            System.out.println("2");
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    /**
     * 首頁路徑
     */
    private String backendIndex = "/backend/index";

    /**
     * 登入頁面路徑
     */
    private String loginPage = "/backend/login";

    /**
     * 註冊頁面路徑
     */
    private String registerPage = "/backend/register";

    /**
     * 忘記密碼頁面路徑
     */
    private String forgotPasswordPage = "/backend/forgotPassword";

    /**
     * 搜尋使用者是否已有登入的記錄
     *
     * @param cookies 傳入使用者的cookies，並於後續使用Stream的filter方法，
     *                於方法內呼叫自定義check()方法將含使用者登入資訊的cookies過濾，
     *                並使用findFirst()方法找出第一個符合條件的Cookie
     * @return 返回Optional物件，如果沒有找到符合條件的cookie，則返回含null的Optional物件
     */
    private Optional<Cookie> userCookie(Cookie[] cookies) {
        return Stream.of(cookies)
                .filter(cookie -> check(cookie))
                .findFirst();
    }

    /**
     * 判斷是否有登入資訊
     *
     * @param cookie 傳入Cookie物件，並呼叫Cookie的getter方法確認使否有符合登入資訊的Cookie
     * @return 如果兩者都符合則返回true，沒有則返回false
     */
    private boolean check(Cookie cookie) {
        return "admNo".equals(cookie.getName()) &&
                "titleNo".equals(cookie.getValue());
    }

    /**
     * Email格式的正則表達式
     */
    private final Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    /**
     * 驗證是否為信箱格式
     *
     * @param email 在登入頁面輸入用戶名或Email時傳入方法內判斷格式
     * @return 返回布林值，如果符合Email格式則返回true，不符合則返回false
     */
    private Boolean validateEmail(String email) {
        return emailRegex.matcher(email).find();
    }

    private Boolean checkURL(String url) {
        return url.equals(loginPage) || url.equals(registerPage) || url.equals(forgotPasswordPage);
    }
}
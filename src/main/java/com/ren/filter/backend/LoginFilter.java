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
import javax.servlet.http.HttpSession;
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
 */
@WebFilter(urlPatterns = {"/backend/*"})
@Component
public class LoginFilter implements Filter {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;
        System.out.println("LoginFilter有在嗎");
        String requestURI = ((HttpServletRequest) req).getRequestURI();

        if (!validateURL(requestURI)) {
            System.out.println("LoginFilter過濾開始");
            // 搜尋使用者cookie確認是否有管理員相關資訊
            Optional<Cookie> userCookie = Optional.ofNullable(((HttpServletRequest) req)
                            .getCookies())
                    .flatMap(this::userCookie);

            HttpSession session = ((HttpServletRequest) req).getSession(false);
            Administrator administrator = null;

            /**
             * ((有無造訪網頁(有無session) && 有無登入(有無administrator)) || 有無設定自動登入(有無cookie))
             * 未造訪網頁(false) || 確認有無 cookie
             * 已造訪網頁(true) && 還未登入(false) || 確認有無 cookie
             * 因考慮使用者可能會選擇關掉cookie or 不使用自動登入功能，將cookie的確認放在 or 判斷式的後面
             * 優先確認使用者登入狀態
             */
            if (session != null && (administrator = (Administrator) session.getAttribute("administrator")) == null ||!userCookie.isPresent()) {
                administrator = (Administrator) session.getAttribute("administrator");
                ((HttpServletResponse) res).sendRedirect(loginPage);
                return;
            }

        }
        System.out.println("LoginFilter結束");
        chain.doFilter(req, res);
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
     * 靜態資源的正則表達式(含登入、註冊、忘記密碼)
     */
    private final Pattern resoucesRegex =
            Pattern.compile(".*\\.(css|js|png|jpg|jpeg|gif|svg|woff|ttf)$|/register|/login|/forgotPassword");

    /**
     * 驗證請求的url是否為靜態資源或為登入、註冊、忘記密碼等網頁
     *
     * @param url 發出請求的網址 request url
     * @return True為靜態資源或登入相關網頁(無須過濾)，False為需過濾頁面
     */
    private Boolean validateURL(String url) {
        return resoucesRegex.matcher(url).find();
    }

}
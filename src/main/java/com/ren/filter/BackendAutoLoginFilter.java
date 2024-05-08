package com.ren.filter;

import com.Entity.Administrator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import static com.ren.util.Constants.loginPage;
import static com.ren.util.Validator.validateURL;

/**
 * 用於確認後台員工登入狀態的過濾器，
 * 主要有二功能:
 * 1.確認登入狀態，如果未登入則導向登入
 * 2.確認自動登入功能
 */
@WebFilter(urlPatterns = "/backend/*")
public class BackendAutoLoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("AutoLoginFilter有在嗎");
        String requestURI = req.getRequestURI();
        System.out.println("進入AutoLoginFilter" + requestURI);
        // 跳過靜態資源與登入相關網頁
        if (!validateURL(requestURI)) {
            System.out.println("被AutoLoginFilter過濾的" + requestURI);
            System.out.println("AutoLoginFilter過濾開始");

            HttpSession session = req.getSession(false);
            Administrator administrator = null;

            // 搜尋使用者cookie確認是否有管理員相關資訊
            Cookie cookie = null;
            Optional<Cookie> userCookie = Optional.ofNullable(req.getCookies()).flatMap(this::userCookie);

            /**
             * ((有無造訪網頁(有無session) || 有無登入(有無administrator)) || 有無設定自動登入(有無autoLogin Cookie))
             * 因考慮使用者可能會關閉Cookie與不使用自動登入功能，且優先判斷cookie有較高的安全性問題，將autoLogin的確認放在 or 判斷式的最後面，
             * 優先確認使用者登入狀態
             * 如果都沒有，導向登入頁面
             */
            if (session == null || (administrator = (Administrator) session.getAttribute("administrator")) == null || !userCookie.isPresent()) {
                res.sendRedirect(loginPage);
                return;
            }

        }
        System.out.println("AutoLoginFilter結束");
        chain.doFilter(req, res);
    }

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
     * 1.從cookie裡面先尋找是否有名字為"autoLogin"的cookie
     * 2.其值為在登入時自動生成的亂數，分別存入Cookie("autoLogin", random)、Redis資料庫(random, "autoLogin")
     * 3.透過Redis的搜尋方法找到相對應的key
     *
     * @param cookie 傳入Cookie物件，並呼叫Cookie的getter方法確認使否有符合登入資訊的Cookie
     * @return 如果兩者都符合則返回true，沒有則返回false
     */
    public boolean check(Cookie cookie) {
        RedisTemplate redisTemplate = new RedisTemplate<>();
        return "autoLogin".equals(cookie.getName()) &&
                cookie.getValue()
                        .equals(redisTemplate.opsForValue()
                                .get(cookie.getValue()));
    }

}
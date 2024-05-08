package com.ren.filter;

import com.Entity.Administrator;
import com.ren.administrator.dto.LoginState;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.ren.util.Constants.loginPage;
import static com.ren.util.Validator.validateURL;

/**
 * 檢視帳號登入狀態，以SessionID比對，如果SessionID與Redis資料庫內的不一樣，強制登出
 */
@WebFilter("/backend/*")
public class BackendLoginStateFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("LoginStateFilter你有在嗎?");
        String requestURI = req.getRequestURI();
        System.out.println("進入AutoLoginFilter" + requestURI);
        HttpSession session = req.getSession(false);
        Administrator administrator = null;
        // 非靜態資源與登入相關網頁 && 已造訪網站 && 已登入
        if (!validateURL(requestURI) && session != null && (administrator = (Administrator) session.getAttribute("administrator")) != null) {
            System.out.println("被LoginStateFilter過濾的" + requestURI);
            Integer admNo = administrator.getAdmNo();
            RedisTemplate redisTemplate = new RedisTemplate<>();
            // 如果當前SessionID與Redis資料庫內的SessionID不同，則代表為不同裝置登入，強制登出
            if (session.getId() != ((LoginState) redisTemplate.opsForValue().get(admNo)).getJsessionid()){
                System.out.println("被強制登出囉");
                session.invalidate();
                res.sendRedirect(loginPage);
                return;
            }
        }

        System.out.println("LoginStateFilter結束");
        chain.doFilter(req, res);
    }
}

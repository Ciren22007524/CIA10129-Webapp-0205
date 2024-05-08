package com.ren.filter.backend;

import com.ren.administrator.dto.LoginState;
import com.ren.administrator.service.AdministratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/backend/*"})
@Component
public class LoginStateFilter implements Filter {

    @Autowired
    private AdministratorServiceImpl administratorSvc;

    @Autowired
    private RedisTemplate<String, LoginState> redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

package com.nhnacademy.shoppingmall.common.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/mypage/*")
public class LoginCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#10 /mypage/ 하위경로의 접근은 로그인한 사용자만 접근할 수 있습니다.
        String userId = (String) req.getSession().getAttribute("userId");
        if (userId == null) {
            log.debug("로그인 하지 않은 사용자입니다.");
            res.sendRedirect("/login.do");
        } else {
            chain.doFilter(req, res);
        }
    }
}
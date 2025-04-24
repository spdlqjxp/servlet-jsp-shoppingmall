package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "adminCheckFilter", urlPatterns = "/admin/*")
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        String userRole = (String) req.getSession().getAttribute("userRole");
        if (userRole != null && userRole.equals("ROLE_ADMIN")) {
            chain.doFilter(req, res);
        } else {
            log.debug("관리자 권한이 없는 사용자입니다.");
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자 권한이 없습니다.");
        }
    }
}

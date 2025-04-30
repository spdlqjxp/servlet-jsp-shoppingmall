package com.nhnacademy.shoppingmall.controller.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/edit.do")
public class MypageEditController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("user") == null) {
            return "redirect:/login.do";
        }
        return "shop/page/mypage/mypage_edit";
    }
}

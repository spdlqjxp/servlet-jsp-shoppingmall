package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserAlreadyExistsException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
public class SignupPostController implements BaseController {
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String name = req.getParameter("user_name");
        String password = req.getParameter("user_password");
        String passwordConfirm = req.getParameter("user_password_confirm");
        String birth = req.getParameter("user_birth");

        if (!password.equals(passwordConfirm)) {
            return "redirect:/signup.do";
        }
        User user = new User(userId, name, password, birth, User.Auth.ROLE_USER, 1_000_000, LocalDateTime.now(), LocalDateTime.now());
        try {
            userService.saveUser(user);

        } catch (UserAlreadyExistsException e) {
            return "redirect:/signup.do";
        }
        return "redirect:/login.do";
    }
}

package com.uhablog.fuyakake.controller;

import com.uhablog.fuyakake.entity.UserMaster;
import com.uhablog.fuyakake.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {

    @Autowired
    private LoginService service;

    /**
     * パスワードエンコーダー
     */
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    /**
     * ユーザー登録処理を行う
     * @param userId
     * @param password
     * @param username
     * @return
     */
    @PostMapping("/signup")
    public String signup(@RequestParam("userId")String userId,
            @RequestParam("password")String password,
            @RequestParam("username")String username) {
        

        // 登録ユーザーインスタンス生成
        UserMaster user = new UserMaster();
        user.setUserId(userId);
        user.setPassword(encoder.encode(password));
        user.setUserName(username);
        boolean result = service.signup(user);

        // 登録に成功したかどうかによって処理を分ける
        if (result) {
            return "login";
        } else {
            return "signup";
        }
    }
}

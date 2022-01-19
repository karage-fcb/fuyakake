package com.uhablog.fuyakake.controller;

import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.ToppageService;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopPageController {

    @Autowired
    private ToppageService service;

    @GetMapping("/")
    public String toppage(Model model) {

        // ログインユーザーID取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        // Topページ用のモデルを取得する
        ToppageModel toppageModel = service.getToppageModel(userId);
        model.addAttribute("model", toppageModel);
        return "html/top";
    }
    
}

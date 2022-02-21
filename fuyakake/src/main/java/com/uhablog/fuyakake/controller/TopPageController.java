package com.uhablog.fuyakake.controller;


import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.ToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TopPageController {

    @Autowired
    private ToppageService service;

    @GetMapping("/")
    public String toppage(
        @RequestParam(name = "displayMonth", required = false)String displayMonth, 
        Model model
    ) {

        // TODO ベースコントローラ作成して、ログインユーザー取得メソッドを作成する
        // ログインユーザーID取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        // Topページ用のモデルを取得する
        ToppageModel toppageModel = service.getToppageModel(userId, displayMonth);
        model.addAttribute("model", toppageModel);
        return "html/top";
    }
    
}

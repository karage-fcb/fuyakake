package com.uhablog.fuyakake.controller;

import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.ToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopPageController {

    @Autowired
    private ToppageService service;

    @GetMapping("/")
    public String toppage(Model model) {

        ToppageModel toppageModel = service.getToppageModel("uhablog");
        System.out.println(toppageModel.getIncomInfoList().get(0));
        model.addAttribute("model", toppageModel);
        return "html/top";
    }
    
}

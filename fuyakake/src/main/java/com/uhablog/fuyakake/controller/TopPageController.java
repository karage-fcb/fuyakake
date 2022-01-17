package com.uhablog.fuyakake.controller;

import com.uhablog.fuyakake.service.ToppageService;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

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

        model.addAttribute(service.getToppageModel("uhablog"));
        return "html/top";
    }
    
}

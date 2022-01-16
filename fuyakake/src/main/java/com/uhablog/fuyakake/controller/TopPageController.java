package com.uhablog.fuyakake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopPageController {

    @GetMapping("/")
    public String toppage() {
        return "html/top";
    }
    
}

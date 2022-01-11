package com.uhablog.fuyakake.controller;

import com.uhablog.fuyakake.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestRepository repository;

    @Autowired
    public TestController(TestRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/")
    public String test() {
        return String.valueOf(repository.findAll());
    }
}

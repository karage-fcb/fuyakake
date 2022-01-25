package com.uhablog.fuyakake.controller.rest;

import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.ToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppage-api")
public class ToppageRestController {

    @Autowired
    private ToppageService service;
    
    /**
     * 消費情報を登録する
     * @param consumption
     * @return 登録結果
     */
    @PostMapping("/insert-consumption")
    public CommitModel insertConsumption(ConsumptionForm consumption) {

        // 返却用モデル
        CommitModel commitModel = new CommitModel();

        // 受け取った消費情報の出力
        System.out.println(consumption);
        System.out.println(consumption.toString());

        // ログインユーザーID取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        // 消費情報登録
        commitModel = service.insertConsumption(userId, consumption);

        return commitModel;
    }

    @GetMapping("/get-consumption")
    public ToppageModel getConsumption(@RequestParam(name = "displayMonth", required = false)String displayMonth) {

        // ログインユーザーID取得
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName();

        // 消費情報取得
        return service.getConsumption(userId, displayMonth);
    }
}

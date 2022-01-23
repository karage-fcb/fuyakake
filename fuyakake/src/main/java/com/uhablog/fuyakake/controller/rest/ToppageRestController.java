package com.uhablog.fuyakake.controller.rest;

import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.model.CommitModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppage-api")
public class ToppageRestController {
    
    @PostMapping("/insert-consumption")
    public CommitModel insertConsumption(ConsumptionForm consumption) {
        CommitModel commitModel = new CommitModel();
        System.out.println(consumption);
        System.out.println(consumption.toString());
        commitModel.setStatusCode(200);
        commitModel.setMessage("ajax test");

        return commitModel;
    }
}

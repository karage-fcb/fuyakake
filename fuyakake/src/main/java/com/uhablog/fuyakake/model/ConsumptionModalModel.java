package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.Account;
import com.uhablog.fuyakake.entity.BigCategory;

import lombok.Data;

@Data
public class ConsumptionModalModel {
    
    /**
     * カテゴリ情報
     */
    private List<BigCategory> categories;

    /**
     * 口座情報
     */
    private List<Account> accounts;
}

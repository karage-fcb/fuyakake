package com.uhablog.fuyakake.service;

import com.uhablog.fuyakake.repository.AccountsRepository;
import com.uhablog.fuyakake.repository.BigCategoryRepository;
import com.uhablog.fuyakake.repository.ConsumptionRepository;
import com.uhablog.fuyakake.repository.IncomRepository;
import com.uhablog.fuyakake.repository.InvestmentRepository;
import com.uhablog.fuyakake.repository.LoginRepository;
import com.uhablog.fuyakake.repository.SelfInvestmentRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class BaseService{
    
    /**
     * ログインリポジトリ
     */
    @Autowired
    private LoginRepository loginRepository;

    /**
     * 収入リポジトリ
     */
    @Autowired
    private IncomRepository incomRepository;

    /**
     * 消費リポジトリ
     */
    @Autowired 
    private ConsumptionRepository consumptionRepository;

    /**
     * 投資リポジトリ
     */
    @Autowired
    private InvestmentRepository investmentRepository;

    /**
     * 自己投資リポジトリ
     */
    @Autowired
    private SelfInvestmentRepository selfInvestmentRepository;

    /**
     * 大カテゴリリポジトリ
     */
    @Autowired
    private BigCategoryRepository bigCategoryRepository;

    /**
     * 口座リポジトリ
     */
    @Autowired
    private AccountsRepository accountsRepository;
}

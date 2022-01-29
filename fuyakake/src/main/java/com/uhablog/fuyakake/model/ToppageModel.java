package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.dto.ToppageConsumption;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.entity.dto.ToppageInvestment;
import com.uhablog.fuyakake.entity.dto.ToppageSelfInvestment;

import lombok.Data;

@Data
public class ToppageModel {
    
    /**
     * 収入情報
     */
    private List<ToppageIncom> incomInfoList;

    /**
     * 収入合計
     */
    private int totalIncom;

    /**
     * 消費情報
     */
    private List<ToppageConsumption> consumptionList;

    /**
     * 消費合計
     */
    private int totalConsumption;

    /**
     * 投資情報
     */
    private List<ToppageInvestment> investmentList;

    /**
     * 投資合計
     */
    private int totalInvestment;

    /**
     * 自己投資情報
     */
    private List<ToppageSelfInvestment> selfInvestmentList;

    /**
     * 自己投資合計
     */
    private int totalSelfInvestment;
}

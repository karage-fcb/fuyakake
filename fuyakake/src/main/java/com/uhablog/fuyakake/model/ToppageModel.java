package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.dto.ToppageConsumption;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;

import lombok.Data;

@Data
public class ToppageModel {
    
    /**
     * 収入情報
     */
    private List<ToppageIncom> incomInfoList;

    /**
     * 消費情報
     */
    private List<ToppageConsumption> consumptionList;
}

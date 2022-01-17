package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.Incom;

import lombok.Data;

@Data
public class ToppageModel {
    
    /**
     * 収入情報
     */
    private List<Incom> incomInfoList;
}

package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.entity.dto.ToppageIncomInfo;

import lombok.Data;

@Data
public class ToppageModel {
    
    /**
     * 収入情報
     */
    private List<ToppageIncom> incomInfoList;
}

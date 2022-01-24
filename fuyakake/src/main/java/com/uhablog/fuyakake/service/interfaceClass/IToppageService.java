package com.uhablog.fuyakake.service.interfaceClass;


import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ToppageModel;

public interface IToppageService {
    
    // 初期表示:Toppageモデル取得
    public ToppageModel getToppageModel(String userId, String getMonth);

    /**
     * 消費入力
     */
    public CommitModel insertConsumption(String userId, ConsumptionForm consumption); 
}

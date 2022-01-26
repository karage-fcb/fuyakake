package com.uhablog.fuyakake.service.interfaceClass;


import java.util.List;

import com.uhablog.fuyakake.entity.MiddleCategory;
import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ToppageModel;

public interface IToppageService {
    
    /**
     * トップページモデルを取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getToppageModel(String userId, String getMonth);

    /**
     * 消費情報を登録する
     * @param userId
     * @param consumption
     * @return
     */
    public CommitModel insertConsumption(String userId, ConsumptionForm consumption); 

    /**
     * 消費情報を取得する
     * @param userId
     * @param getMonth
     * @return 消費情報
     */
    public ToppageModel getConsumption(String userId, String getMonth);

    /**
     * カテゴリ情報を取得する
     * @param userId
     * @return
     */
    public List<MiddleCategory> getCategory(String userId);
}

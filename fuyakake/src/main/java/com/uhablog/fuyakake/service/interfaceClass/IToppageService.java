package com.uhablog.fuyakake.service.interfaceClass;


import java.util.List;

import com.uhablog.fuyakake.entity.BigCategory;
import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.entity.form.IncomForm;
import com.uhablog.fuyakake.entity.form.InvestmentForm;
import com.uhablog.fuyakake.entity.form.SelfInvestmentFrom;
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
     * カテゴリ情報を取得する
     * @param userId
     * @return
     */
    public List<BigCategory> getCategory(String userId);

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
     * 収入情報を登録する
     * @param userId
     * @param incom
     * @return
     */
    public CommitModel insertIncom(String userId, IncomForm incom);

    /**
     * 収入情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getIncom(String userId, String getMonth);

    /**
     * 投資情報を登録する
     * @param userId
     * @param getMonth
     * @return
     */
    public CommitModel insertInvestment(String userId, InvestmentForm investment);

    /**
     * 投資情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getInvestment(String userId, String getMonth);

    /**
     * 自己投資情報を登録する
     * @param userId
     * @param getMonth
     * @return
     */
    public CommitModel insertSelfInvestment(String userId, SelfInvestmentFrom selfInvestment);

    /**
     * 自己投資情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getSelfInvestment(String userId, String getMonth);
}
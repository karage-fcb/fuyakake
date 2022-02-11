package com.uhablog.fuyakake.service.interfaceClass;


import com.uhablog.fuyakake.common.FuyakakeException;
import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.entity.form.IncomForm;
import com.uhablog.fuyakake.entity.form.InvestmentForm;
import com.uhablog.fuyakake.entity.form.SelfInvestmentFrom;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ConsumptionModalModel;
import com.uhablog.fuyakake.model.ModalModel;
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
    public ConsumptionModalModel getConsumptionModal(String userId);

    /**
     * 収入情報入力時の口座・カテゴリ情報を取得する
     * @param userId
     * @return
     */
    public ModalModel getIncomModalModel(String userId);

    /**
     * 投資情報入力時の口座情報・カテゴリ情報を取得する
     * @param userId
     * @return
     */
    public ModalModel getInvestmentModelModal(String userId);

    /**
     * 自己投資入力時の口座・カテゴリ情報を取得する
     */
    public ModalModel getSelfInvestmentModalModel(String userId);

    /**
     * 消費情報を登録する
     * @param userId
     * @param consumption
     * @return
     */
    public CommitModel insertConsumption(String userId, ConsumptionForm consumption) throws FuyakakeException; 

    /**
     * 収入情報を登録する
     * @param userId
     * @param incom
     * @return
     */
    public CommitModel insertIncom(String userId, IncomForm incom) throws FuyakakeException;

    /**
     * 投資情報を登録する
     * @param userId
     * @param getMonth
     * @return
     */
    public CommitModel insertInvestment(String userId, InvestmentForm investment) throws FuyakakeException;

    /**
     * 自己投資情報を登録する
     * @param userId
     * @param getMonth
     * @return
     */
    public CommitModel insertSelfInvestment(String userId, SelfInvestmentFrom selfInvestment) throws FuyakakeException;

    /**
     * 消費情報を取得する
     * @param userId
     * @param getMonth
     * @return 消費情報
     */
    public ToppageModel getConsumption(String userId, String getMonth);
    
    /**
     * 収入情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getIncom(String userId, String getMonth);

    /**
     * 投資情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getInvestment(String userId, String getMonth);

    /**
     * 自己投資情報を取得する
     * @param userId
     * @param getMonth
     * @return
     */
    public ToppageModel getSelfInvestment(String userId, String getMonth);
}
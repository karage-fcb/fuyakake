package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.uhablog.fuyakake.entity.Consumption;
import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.Investment;
import com.uhablog.fuyakake.entity.SelfInvestment;
import com.uhablog.fuyakake.entity.dto.ToppageConsumption;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.entity.dto.ToppageInvestment;
import com.uhablog.fuyakake.entity.dto.ToppageSelfInvestment;
import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.entity.form.IncomForm;
import com.uhablog.fuyakake.entity.form.InvestmentForm;
import com.uhablog.fuyakake.entity.form.SelfInvestmentFrom;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ModalModel;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;

@Service
public class ToppageService extends BaseService implements IToppageService {

    /**
     * 初期表示時のToppageモデルを取得する
     */
    @Override
    public ToppageModel getToppageModel(String userId, String getMonth){

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末日を取得する
        // TODO 動的に取得したい
        Date startDate = Date.valueOf("2022-01-01");
        Date endDate = Date.valueOf("2022-01-31");

        // 収入情報取得メソッド呼び出し
        model = getIncom(model, userId, startDate, endDate);

        // 消費情報取得メソッド呼び出し
        model = getConsumption(model, userId, startDate, endDate);

        // 投資情報取得メソッド呼び出し
        model = getInvestment(model, userId, startDate, endDate);

        // 自己投資情報取得メソッド呼び出し
        model = getSelfInvestment(model, userId, startDate, endDate);

        // 利益 = 収入 - 消費
        model.setRevenue(model.getTotalIncom() - model.getTotalConsumption());

        // 貯金 = 利益 - 投資
        // TODO マイナスはあり得ない
        model.setDeposit(model.getRevenue() - model.getTotalInvestment() - model.getTotalSelfInvestment());

        // 口座情報取得
        model.setAccountList(getAccountsRepository().getAccounts(userId));

        // 合計資産額取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));

        return model;
    }

    /**
     * カテゴリ情報を取得する
     */
    @Override
    public ModalModel getConsumptionModal(String userId) {

        // モデル
        ModalModel model = new ModalModel();

        System.out.println("ToppageService getConsumptionModal カテゴリ情報取得");
        // カテゴリ情報取得
        model.setCategoryList(getBigCategoryRepository().findAll());
        System.out.println("ToppageService getConsumptionModal カテゴリ情報取得終了");
        // System.out.println(model.getCategoryList().toString());

        // 口座情報取得
        model.setAccountList(getAccountsRepository().getAllAccount(userId));

        return model;
    }

    /**
     * 収入情報入力時の口座・カテゴリ情報の取得
     */
    @Override
    public ModalModel getIncomModalModel(String userId) {

        // モデル
        ModalModel model = new ModalModel();

        // 口座情報取得
        model.setAccountList(getAccountsRepository().getAllAccount(userId));
        return model;
    }

    /**
     * 投資情報入力時の口座情報・カテゴリ情報を取得する
     */
    @Override
    public ModalModel getInvestmentModelModal(String userId) {
        // モデル
        ModalModel model = new ModalModel();

        // 口座情報取得
        model.setAccountList(getAccountsRepository().getAllAccount(userId));

        return model;
    }

    /**
     * 自己投資入力時の口座・カテゴリ情報を取得する
     */
    @Override
    public ModalModel getSelfInvestmentModalModel(String userId) {
        // モデル
        ModalModel model = new ModalModel();

        // 口座情報取得
        model.setAccountList(getAccountsRepository().getAllAccount(userId));

        return model;
    }

    /**
     * 消費入力
     */
    @Override
    public CommitModel insertConsumption(String userId, ConsumptionForm consumptionForm) {

        System.out.println("insertConsumptionメソッド呼び出されました");
        // 消費情報登録
        int ret = getConsumptionRepository().insertConsumption(
            consumptionForm.getMoney(),
            consumptionForm.getAccountId(),
            userId,
            consumptionForm.getCategoryId(),
            consumptionForm.getMemo(),
            consumptionForm.getDate()
        );
        CommitModel commitModel = new CommitModel();

        // 登録に成功した時
        if (ret == 1) {
            commitModel.setError(false);
            commitModel.setMessage("消費情報登録成功!");
        } else {
            commitModel.setError(true);
            commitModel.setMessage("消費情報登録失敗!");
        }
        return commitModel;
    }

    /**
     * 対象年月の消費情報を取得する
     * @param userId
     * @param getMonth
     * @return 消費情報、合計消費額が入ったモデル
     */
    @Override
    public ToppageModel getConsumption(String userId, String getMonth) {

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末日を取得する
        Date startDate = Date.valueOf("2022-01-01");
        Date endDate = Date.valueOf("2022-01-31");

        // 消費情報取得
        model = getConsumption(model, userId, startDate, endDate);
        return model;
    }

    /**
     * 収入情報入力
     */
    @Override
    public CommitModel insertIncom(String userId, IncomForm incom) {

        System.out.println("収入情報入力" + incom.toString());
        // 収入情報登録
        int ret = getIncomRepository().insertIncom(
            incom.getMoney(),
            incom.getAccountId(),
            userId,
            incom.getCategoryId(),
            incom.getMemo(),
            incom.getDate()
        );
        CommitModel commitModel = new CommitModel();

        // 登録に成功したかどうか判定する
        if(ret == 1) {
            commitModel.setError(false);
            commitModel.setMessage("収入情報登録成功!");
        } else {
            commitModel.setError(true);
            commitModel.setMessage("収入情報登録失敗!");
        }
        return commitModel;
    }

    /**
     * 収入情報取得
     */
    @Override
    public ToppageModel getIncom(String userId, String getMonth) {

        // 収入情報取得
        System.out.println("収入情報取得");
        
        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末尾を取得する
        // TODO 動的に年月を変更
        Date startDate = Date.valueOf("2022-01-01");
        Date endDate = Date.valueOf("2022-01-31");

        // 収入情報取得メソッド呼び出し
        model = getIncom(model, userId, startDate, endDate);

        return model;
    }

    /**
     * 投資情報登録
     */
	@Override
	public CommitModel insertInvestment(String userId, InvestmentForm investment) {
        System.out.println("投資情報入力" + investment.toString());

        // 投資情報登録
        int ret = getInvestmentRepository().insertSelfInvestment(
            investment.getMoney(),
            investment.getAccountId(),
            userId,
            investment.getCategoryId(),
            investment.getMemo(),
            investment.getDate()
        );

        CommitModel model = new CommitModel();

        // 登録成功したかどうか判定する
        if(ret == 1) {
            model.setError(false);
            model.setMessage("投資情報登録成功!");
        } else {
            model.setError(true);
            model.setMessage("投資情報登録失敗!");
        }
		return model;
	}

    /**
     * 投資情報取得
     */
	@Override
	public ToppageModel getInvestment(String userId, String getMonth) {
        // 投資情報取得
        System.out.println("投資情報取得");

        ToppageModel model = new ToppageModel();

        // TODO 動的に年月を変更
        Date startDate = Date.valueOf("2022-01-01");
        Date endDate = Date.valueOf("2022-01-31");

        model = getInvestment(model, userId, startDate, endDate);

		return model;
	}

    /**
     * 自己投資情報登録
     */
	@Override
	public CommitModel insertSelfInvestment(String userId, SelfInvestmentFrom selfInvestment) {
        System.out.println("自己投資情報入力" + selfInvestment.toString());

        // 自己投資情報登録
        int ret = getSelfInvestmentRepository().insertSelfInvestment(
            selfInvestment.getMoney(),
            selfInvestment.getAccountId(),
            userId,
            selfInvestment.getCategoryId(),
            selfInvestment.getMemo(),
            selfInvestment.getDate()
        );

        CommitModel commitModel = new CommitModel();

        // 登録に成功したかどうか判定
        if(ret == 1) {
            commitModel.setError(false);
            commitModel.setMessage("自己投資情報登録成功");
        } else {
            commitModel.setError(true);
            commitModel.setMessage("自己投資情報登録失敗");
        }

		return commitModel;
	}

    /**
     * 自己投資情報取得
     */
	@Override
	public ToppageModel getSelfInvestment(String userId, String getMonth) {

        // 自己投資情報取得
        System.out.println("自己投資情報取得");

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末尾を取得する
        // TODO 年月の取得を動的に変換
        Date startDate = Date.valueOf("2022-01-01");
        Date endDate = Date.valueOf("2022-01-31");

        // 自己投資情報取得メソッド呼び出し
        model = getSelfInvestment(model, userId, startDate, endDate);

		return model;
	}

    /**
     * 収入情報取得
     * @param model
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    private ToppageModel getIncom(ToppageModel model, String userId, Date startDate, Date endDate) {
        // 収入情報取得
        List<Incom> incomList = getIncomRepository().getIncom(userId, startDate, endDate);
        List<ToppageIncom> toppageIncomList = new ArrayList<>();

        // Toppageに渡す収入情報のリストを作成
        for (Incom incom : incomList) {
            ToppageIncom toppageIncom = new ToppageIncom();
            toppageIncom.setPrice(incom.getIncomMoney());
            toppageIncom.setCategoryName(incom.getCategory().getCategoryName());
            toppageIncomList.add(toppageIncom);
        }
        model.setIncomInfoList(toppageIncomList);

        // 収入合計値取得
        try {
            model.setTotalIncom(getIncomRepository().getTotalIncom(userId, startDate, endDate));
        } catch (Exception e) {
            model.setTotalIncom(0);
        } 

        System.out.println("収入情報取得" + model.toString());
        return model;
    }

    /**
     * 消費情報取得
     * @param model
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    private ToppageModel getConsumption(ToppageModel model, String userId, Date startDate, Date endDate){

        // 消費情報取得
        List<Consumption> consumptionList = getConsumptionRepository().getConsumption(userId, startDate, endDate);
        List<ToppageConsumption> toppageConsumptionList = new ArrayList<>();

        // Toppageに渡す消費情報のリストを作成
        for (Consumption consumption: consumptionList) {
            ToppageConsumption toppageConsumption = new ToppageConsumption();
            toppageConsumption.setPrice(consumption.getConsumptionMoney());
            toppageConsumption.setCategoryName(consumption.getCategory().getCategoryName());
            toppageConsumptionList.add(toppageConsumption);
        }
        model.setConsumptionList(toppageConsumptionList);

        // 消費合計値取得
        try {
            model.setTotalConsumption(getConsumptionRepository().getTotalConsumption(userId, startDate, endDate));
        } catch (Exception e) {
            model.setTotalConsumption(0);
        }

        return model;

    }

    /**
     * 投資情報取得
     * @param model
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    private ToppageModel getInvestment(ToppageModel model, String userId, Date startDate, Date endDate) {

        // 投資情報取得
        List<Investment> investments = getInvestmentRepository().getInvestment(userId, startDate, endDate);
        List<ToppageInvestment> toppageInvestments = new ArrayList<>();

        // Toppageに渡す投資情報のリストを作成
        for (Investment investment: investments) {
            ToppageInvestment toppageInvestment = new ToppageInvestment();
            toppageInvestment.setPrice(investment.getInvestmentMoney());
            toppageInvestment.setCategoryName(investment.getCategory().getCategoryName());
            toppageInvestments.add(toppageInvestment);
        }
        model.setInvestmentList(toppageInvestments);

        // 投資合計値取得
        try {
            model.setTotalInvestment(getInvestmentRepository().getTotalInvestment(userId, startDate, endDate));
        } catch (Exception e) {
            model.setTotalInvestment(0);
        }

        return model;
    }

    /**
     * 自己投資情報取得
     * @param model
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    private ToppageModel getSelfInvestment(ToppageModel model, String userId, Date startDate, Date endDate){
        System.out.println("Service getSelfInvestment()");

        // 自己投資情報取得
        List<SelfInvestment> selfInvestments = getSelfInvestmentRepository().getSelfInvestment(userId, startDate, endDate);
        List<ToppageSelfInvestment> toppageSelfInvestments = new ArrayList<>();

        // Toppageに渡す自己投資情報のリストを作成
        for (SelfInvestment selfInvestment: selfInvestments) {
            ToppageSelfInvestment toppageSelfInvestment = new ToppageSelfInvestment();
            toppageSelfInvestment.setPrice(selfInvestment.getInvestmentMoney());
            toppageSelfInvestment.setCategoryName(selfInvestment.getCategory().getCategoryName());
            toppageSelfInvestments.add(toppageSelfInvestment);
        }
        model.setSelfInvestmentList(toppageSelfInvestments);

        // 自己投資合計値取得
        try {
            model.setTotalSelfInvestment(getSelfInvestmentRepository().getTotalSelfInvestment(userId, startDate, endDate));
        } catch (AopInvocationException e) {
            // 対象データが存在しない時は0を設定する
            model.setTotalSelfInvestment(0);
        }

        System.out.println(model.toString());
        return model;
        
    }
}

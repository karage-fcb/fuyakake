package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.uhablog.fuyakake.common.Constant;
import com.uhablog.fuyakake.common.DateUtil;
import com.uhablog.fuyakake.common.FuyakakeException;
import com.uhablog.fuyakake.entity.Account;
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
import com.uhablog.fuyakake.model.ConsumptionModalModel;
import com.uhablog.fuyakake.model.ModalModel;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

import org.springframework.aop.AopInvocationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ToppageService extends BaseService implements IToppageService {

    /**
     * 初期表示時のToppageモデルを取得する
     */
    @Override
    public ToppageModel getToppageModel(String userId, String displayMonth){

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末日を取得する
        Date[] days = DateUtil.getFirstDayAndLastDay(displayMonth);

        // 収入情報取得メソッド呼び出し
        model = getIncom(model, userId, days[0], days[1]);

        // 消費情報取得メソッド呼び出し
        model = getConsumption(model, userId, days[0], days[1]);

        // 投資情報取得メソッド呼び出し
        model = getInvestment(model, userId, days[0], days[1]);

        // 自己投資情報取得メソッド呼び出し
        model = getSelfInvestment(model, userId, days[0], days[1]);

        // 利益 = 収入 - 消費
        model.setRevenue(model.getTotalIncom() - model.getTotalConsumption());

        // 貯金 = 利益 - 投資
        // TODO マイナスはあり得ない
        model.setDeposit(model.getRevenue() - model.getTotalInvestment() - model.getTotalSelfInvestment());

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAccounts(userId));

        // 合計資産額取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));

        // 表示年月の設定
        String[] displayMonthStrings = new String[3];
        displayMonthStrings = DateUtil.getStringDisplayMonth(displayMonth);
        model.setPrevMonth("http://127.0.0.1:8080/?displayMonth=" + displayMonthStrings[0]);
        model.setDisplayMonth(displayMonthStrings[1]);
        model.setNextMonth("http://127.0.0.1:8080/?displayMonth=" + displayMonthStrings[2]);

        return model;
    }

    /**
     * カテゴリ情報を取得する
     */
    @Override
    public ConsumptionModalModel getConsumptionModal(String userId) {

        // モデル
        ConsumptionModalModel model = new ConsumptionModalModel();

        // カテゴリ情報取得
        model.setCategories(getBigCategoryRepository().getBigCategory(userId));

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAllAccount(userId));

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
        model.setAccounts(getAccountsRepository().getAllAccount(userId));

        // カテゴリ情報取得
        model.setCategories(getBigCategoryRepository().getOneCategory(userId, Constant.INCOM_BIG_CATEGORY_ID));
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
        model.setAccounts(getAccountsRepository().getAllAccount(userId));

        // カテゴリ情報取得
        model.setCategories(getBigCategoryRepository().getOneCategory(userId, Constant.INVESTMENT_BIG_CATEGORY_ID));

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
        model.setAccounts(getAccountsRepository().getAllAccount(userId));

        // カテゴリ情報取得
        model.setCategories(getBigCategoryRepository().getOneCategory(userId, Constant.SELF_INVESTMENT_BIG_CATEGORY_ID));

        return model;
    }

    /**
     * 消費入力
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommitModel insertConsumption(String userId, ConsumptionForm consumptionForm) 
        throws FuyakakeException{

        // 消費情報登録
        int ret = getConsumptionRepository().insertConsumption(
            consumptionForm.getMoney(),
            consumptionForm.getAccountId(),
            userId,
            consumptionForm.getCategoryId(),
            consumptionForm.getMemo(),
            consumptionForm.getDate()
        );

        // 登録に失敗した時
        if (ret != 1) {
            throw new FuyakakeException("消費情報登録失敗!");
        } 

        // 口座がマイナスにならないか確認
        balanceConfirmation(consumptionForm.getAccountId(), consumptionForm.getMoney());

        // 消費情報を口座に反映
        ret = getAccountsRepository().substractMoney(consumptionForm.getAccountId(), consumptionForm.getMoney());

        // 消費情報の反映に失敗
        if(ret != 1) {
            throw new FuyakakeException("口座情報に反映失敗");
        }

        // 返却用モデル
        CommitModel commitModel = new CommitModel();
        commitModel.setError(false);
        commitModel.setMessage("消費情報登録成功!");
        return commitModel;
    }

    /**
     * 収入情報入力
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommitModel insertIncom(String userId, IncomForm incom) 
        throws FuyakakeException{

        // 収入情報登録
        int ret = getIncomRepository().insertIncom(
            incom.getMoney(),
            incom.getAccountId(),
            userId,
            incom.getCategoryId(),
            incom.getMemo(),
            incom.getDate()
        );

        // 登録に成功したかどうか判定する
        if(ret != 1) {
            throw new FuyakakeException("収入情報登録失敗");
        } 

        // 収入情報を口座に反映
        ret = getAccountsRepository().addMoney(incom.getAccountId(), incom.getMoney());

        // 口座情報の反映に成功
        if(ret != 1) {
            throw new FuyakakeException("口座情報に反映失敗");
        }

        // 返却用モデル
        CommitModel commitModel = new CommitModel();
        commitModel.setMessage("収入情報登録成功!");
        commitModel.setError(false);
        return commitModel;
    }

    /**
     * 投資情報登録
     */
	@Override
	public CommitModel insertInvestment(String userId, InvestmentForm investment)
        throws FuyakakeException {
        System.out.println(" ToppageService line 239 投資情報入力" + investment.toString());

        // 投資情報登録
        int ret = getInvestmentRepository().insertSelfInvestment(
            investment.getMoney(),
            investment.getAccountId(),
            investment.getToAccountId(),
            userId,
            investment.getCategoryId(),
            investment.getMemo(),
            investment.getDate()
        );

        // 登録成功したかどうか判定する
        if(ret != 1) {
            throw new FuyakakeException("投資情報登録失敗");
        }

        // 口座がマイナスにならないか確認
        balanceConfirmation(investment.getAccountId(), investment.getMoney());

        // 金額を投資元から差し引く
        ret = getAccountsRepository().substractMoney(investment.getAccountId(), investment.getMoney());

        if (ret != 1) {
            throw new FuyakakeException("口座情報に反映失敗(投資元)");
        }

        // 口座情報に投資情報を反映
        ret = getAccountsRepository().addMoney(investment.getToAccountId(), investment.getMoney());

        if (ret != 1) {
            throw new FuyakakeException("口座情報に反映失敗(投資先)");
        }

        CommitModel model = new CommitModel();
        model.setError(false);
        model.setMessage("投資情報反映成功");
    	return model;
	}

    /**
     * 自己投資情報登録
     */
	@Override
	public CommitModel insertSelfInvestment(String userId, SelfInvestmentFrom selfInvestment)
        throws FuyakakeException {
        System.out.println("ToppageService line 286 自己投資情報入力" + selfInvestment.toString());

        // 自己投資情報登録
        int ret = getSelfInvestmentRepository().insertSelfInvestment(
            selfInvestment.getMoney(),
            selfInvestment.getAccountId(),
            userId,
            selfInvestment.getCategoryId(),
            selfInvestment.getMemo(),
            selfInvestment.getDate()
        );

        // 登録に成功したかどうか判定
        if(ret != 1) {
            throw new FuyakakeException("自己投資情報登録失敗");
        }

        // 自己投資情報を口座に反映
        ret = getAccountsRepository().substractMoney(selfInvestment.getAccountId(), selfInvestment.getMoney());

        if(ret != 1) {
            throw new FuyakakeException("自己投資情報の口座への反映失敗");
        }

        CommitModel commitModel = new CommitModel();
        commitModel.setMessage("自己投資情報登録成功");
        commitModel.setError(false);
		return commitModel;
	}

    /**
     * 対象年月の消費情報を取得する
     * @param userId
     * @param getMonth
     * @return 消費情報、合計消費額, 口座情報, トータル資産が入ったモデル
     */
    @Override
    public ToppageModel getConsumption(String userId, String displayMonth) {

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末日を取得する
        Date[] days = DateUtil.getFirstDayAndLastDay(displayMonth);

        // 消費情報取得
        model = getConsumption(model, userId, days[0], days[1]);

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAccounts(userId));

        // 合計資産取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));
        return model;
    }

    /**
     * 収入情報取得
     */
    @Override
    public ToppageModel getIncom(String userId, String displayMonth) {

        // 収入情報取得
        System.out.println("ToppageService line 349 収入情報取得");
        
        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末尾を取得する
        Date[] days = DateUtil.getFirstDayAndLastDay(displayMonth);

        // 収入情報取得メソッド呼び出し
        model = getIncom(model, userId, days[0], days[1]);

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAccounts(userId));

        // 合計資産取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));
        return model;
    }

    /**
     * 投資情報取得
     */
	@Override
	public ToppageModel getInvestment(String userId, String displayMonth) {
        // 投資情報取得
        System.out.println("ToppageService line 374 投資情報取得");

        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末尾を取得する
        Date[] days = DateUtil.getFirstDayAndLastDay(displayMonth);

        // 投資情報取得メソッド呼び出し
        model = getInvestment(model, userId, days[0], days[1]);

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAccounts(userId));

        // 合計資産取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));

		return model;
	}

    /**
     * 自己投資情報取得
     */
	@Override
	public ToppageModel getSelfInvestment(String userId, String displayMonth) {

        // 自己投資情報取得
        System.out.println("ToppageService line 400 自己投資情報取得");

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 表示する年月の最初と末尾を取得する
        Date[] days = DateUtil.getFirstDayAndLastDay(displayMonth);

        // 自己投資情報取得メソッド呼び出し
        model = getSelfInvestment(model, userId, days[0], days[1]);

        // 口座情報取得
        model.setAccounts(getAccountsRepository().getAccounts(userId));

        // 合計資産取得
        model.setTotalAsset(getAccountsRepository().getTotalAsset(userId));

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

        return model;

    }

    /**
     * 指定されたIDの口座を指定された金額が超えないかどうか。
     * 超える場合はFuyakakeException発生
     * @param accountId
     * @param money
     * @throws FuyakakeException
     */
    private void balanceConfirmation(int accountId, int money) throws FuyakakeException{

        Account account = getAccountsRepository().getOneAccount(accountId);

        if (account.getAssetAmount() < money) {
            throw new FuyakakeException("口座の残高を支出額が超えています");
        }
    }
}

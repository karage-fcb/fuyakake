package com.uhablog.fuyakake.controller.rest;

import com.uhablog.fuyakake.common.FuyakakeException;
import com.uhablog.fuyakake.entity.form.ConsumptionForm;
import com.uhablog.fuyakake.entity.form.IncomForm;
import com.uhablog.fuyakake.entity.form.InvestmentForm;
import com.uhablog.fuyakake.entity.form.SelfInvestmentFrom;
import com.uhablog.fuyakake.model.CommitModel;
import com.uhablog.fuyakake.model.ModalModel;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.service.ToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/toppage-api")
public class ToppageRestController {

    @Autowired
    private ToppageService service;
    
    /**
     * 消費情報を登録する
     * @param consumption
     * @return 登録結果
     */
    @PostMapping("/insert-consumption")
    public CommitModel insertConsumption(ConsumptionForm consumption) {

        // 返却用モデル
        CommitModel commitModel = new CommitModel();

        // 消費情報登録
        try {
            commitModel = service.insertConsumption(getLoginUserId(), consumption);
        } catch (FuyakakeException e) {
            commitModel.setError(true);
            commitModel.setMessage(e.getMessage());
        }

        // 消費情報取得
        commitModel.setToppageModel(service.getConsumption(getLoginUserId(), "2022-01-01"));

        return commitModel;
    }

    /**
     * カテゴリ情報を取得する
     * @return
     */
    @GetMapping("/show-consumption-modal")
    public ModalModel getConsumptionModal() {
        // TODO カテゴリ情報と口座情報の返却

        return service.getConsumptionModal(getLoginUserId());
    }

    /**
     * 収入入力時のカテゴリ情報を取得する
     */
    @GetMapping("/show-incom-modal")
    public ModalModel getIncomModal() {

        return service.getIncomModalModel(getLoginUserId());
    }

    /**
     * 投資情報入力時の口座・カテゴリを取得する
     * @return
     */
    @GetMapping("/show-investment-modal")
    public ModalModel getInvestmentModal() {
        return service.getInvestmentModelModal(getLoginUserId());
    }

    /**
     * 自己投資情報入力時の口座・カテゴリを取得する
     * @return
     */
    @GetMapping("/show-self-investment-modal")
    public ModalModel getSelfInvestmentModal() {
        return service.getSelfInvestmentModalModel(getLoginUserId());
    }

    /**
     * 収入情報を登録する
     * @param incom
     * @return
     */
    @PostMapping("/insert-incom")
    public CommitModel insertIncom(IncomForm incom) {

        CommitModel commitModel = new CommitModel();

        // 収入情報登録
        commitModel = service.insertIncom(getLoginUserId(), incom);
        return commitModel;
    }

    /**
     * 投資情報登録
     * @param investment
     * @return
     */
    @PostMapping("/insert-investment")
    public CommitModel insertInvestment(InvestmentForm investment) {
        
        // 返却用モデル
        CommitModel model = new CommitModel();

        // 投資情報登録
        model = service.insertInvestment(getLoginUserId(), investment);

        return model;
    }

    /**
     * 自己投資情報登録
     * @param selfInvestment
     * @return
     */
    @PostMapping("/insert-self-investment")
    public CommitModel insertSelfInvestment(SelfInvestmentFrom selfInvestment) {
        // 返却用モデル
        CommitModel model = new CommitModel();

        // 投資情報登録
        model = service.insertSelfInvestment(getLoginUserId(), selfInvestment);

        return model;
    }

    /**
     * ログイン中のユーザー情報を取得する
     * @return ログイン中のユーザーID
     */
    private String getLoginUserId() {
        // ログインユーザーIDを取得して返却する
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    /**
     * 例外発生時の処理
     */
}

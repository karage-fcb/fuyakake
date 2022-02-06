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

        // 受け取った消費情報の出力
        System.out.println(consumption);
        System.out.println(consumption.toString());

        // 消費情報登録
        try {
            commitModel = service.insertConsumption(getLoginUserId(), consumption);
        } catch (FuyakakeException e) {
            commitModel.setError(true);
            commitModel.setMessage(e.getMessage());
        }

        return commitModel;
    }

    /**
     * 指定された年月の消費情報を取得する
     * @param displayMonth
     * @return
     */
    @GetMapping("/get-consumption")
    public ToppageModel getConsumption(@RequestParam(name = "displayMonth", required = false)String displayMonth) {

        // 消費情報取得
        return service.getConsumption(getLoginUserId(), displayMonth);
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
     * 収入情報を取得する
     * @param displayMonth
     * @return
     */
    @GetMapping("/get-incom")
    public ToppageModel getIncom(@RequestParam(name = "displayMonth", required = false)String displayMonth) {
        // 収入情報取得
        return service.getIncom(getLoginUserId(), displayMonth);
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
     * 投資情報取得
     * @param displayMonth
     * @return
     */
    @GetMapping("/get-investment")
    public ToppageModel getInvestment(@RequestParam(name = "displayMonth", required = false)String displayMonth) {
        return service.getInvestment(getLoginUserId(), displayMonth);
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
     * 自己投資情報取得
     * @param displayMonth
     * @return
     */
    @GetMapping("/get-self-investment")
    public ToppageModel getSeflInvestment(@RequestParam(name = "displayMonth", required = false)String displayMonth) {
        System.out.println("Controller getSelfInvestment()");
        return service.getSelfInvestment(getLoginUserId(), displayMonth);
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

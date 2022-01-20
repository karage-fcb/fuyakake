package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.util.List;

import com.uhablog.fuyakake.entity.Consumption;
import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.Investment;
import com.uhablog.fuyakake.entity.dto.ToppageConsumption;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.entity.dto.ToppageInvestment;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.repository.ConsumptionRepository;
import com.uhablog.fuyakake.repository.IncomRepository;
import com.uhablog.fuyakake.repository.InvestmentRepository;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppageService implements IToppageService {

    @Autowired
    private IncomRepository incomRepository;

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private InvestmentRepository investmentRepository;

    /**
     * 初期表示時のToppageモデルを取得する
     */
    @Override
    public ToppageModel getToppageModel(String userId, String getMonth) {

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 収入情報取得
        List<Incom> incomList = incomRepository.getIncom(userId, getMonth, getMonth);
        List<ToppageIncom> toppageIncomList = new ArrayList<>();

        // Toppageに渡す収入情報のリストを作成
        for (Incom incom : incomList) {
            ToppageIncom toppageIncom = new ToppageIncom();
            toppageIncom.setPrice(incom.getIncomMoney());
            toppageIncom.setCategoryName(incom.getCateogry().getCategoryName());
            toppageIncomList.add(toppageIncom);
        }
        model.setIncomInfoList(toppageIncomList);

        // 収入合計値取得
        model.setTotalIncom(incomRepository.getTotalIncom(userId));

        // 消費情報取得
        List<Consumption> consumptionList = consumptionRepository.getConsumption(userId);
        List<ToppageConsumption> toppageConsumptionList = new ArrayList<>();

        // Toppageに渡す消費情報のリストを作成
        for (Consumption consumption: consumptionList) {
            ToppageConsumption toppageConsumption = new ToppageConsumption();
            toppageConsumption.setPrice(consumption.getConsumptionMoney());
            toppageConsumption.setCategoryName(consumption.getCateogry().getCategoryName());
            toppageConsumptionList.add(toppageConsumption);
        }
        model.setConsumptionList(toppageConsumptionList);

        // 消費合計値取得
        model.setTotalConsumption(consumptionRepository.getTotalConsumption(userId));

        // 投資情報取得
        List<Investment> investments = investmentRepository.getInvestment(userId);
        List<ToppageInvestment> toppageInvestments = new ArrayList<>();

        // Toppageに渡す投資情報のリストを作成
        for (Investment investment: investments) {
            ToppageInvestment toppageInvestment = new ToppageInvestment();
            toppageInvestment.setPrice(investment.getInvestmentMoney());
            toppageInvestment.setCategoryName(investment.getCateogry().getCategoryName());
            toppageInvestments.add(toppageInvestment);
        }
        model.setInvestmentList(toppageInvestments);

        // 投資合計値取得
        model.setTotalInvestment(investmentRepository.getTotalInvestment(userId));

        return model;
    }
    
}

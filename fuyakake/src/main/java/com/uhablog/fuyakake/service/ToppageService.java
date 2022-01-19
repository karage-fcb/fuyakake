package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.util.List;

import com.uhablog.fuyakake.entity.Consumption;
import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.dto.ToppageConsumption;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.repository.ConsumptionRepository;
import com.uhablog.fuyakake.repository.IncomRepository;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppageService implements IToppageService {

    @Autowired
    private IncomRepository incomRepository;

    @Autowired
    private ConsumptionRepository consumptionRepository;

    /**
     * 初期表示時のToppageモデルを取得する
     */
    @Override
    public ToppageModel getToppageModel(String userId) {

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 収入情報取得
        List<Incom> incomList = incomRepository.getIncom(userId);
        List<ToppageIncom> toppageIncomList = new ArrayList<>();

        // Toppageに渡す収入情報のリストを作成
        for (Incom incom : incomList) {
            ToppageIncom toppageIncom = new ToppageIncom();
            toppageIncom.setPrice(incom.getIncomMoney());
            toppageIncom.setCategoryName(incom.getCateogry().getCategoryName());
            toppageIncomList.add(toppageIncom);
        }
        model.setIncomInfoList(toppageIncomList);

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

        return model;
    }
    
}

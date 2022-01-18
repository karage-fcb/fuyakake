package com.uhablog.fuyakake.service;

import java.util.ArrayList;
import java.util.List;

import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.dto.ToppageIncom;
import com.uhablog.fuyakake.model.ToppageModel;
import com.uhablog.fuyakake.repository.IncomRepository;
import com.uhablog.fuyakake.service.interfaceClass.IToppageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToppageService implements IToppageService {

    @Autowired
    private IncomRepository incomRepository;

    /**
     * 初期表示時のToppageモデルを取得する
     */
    @Override
    public ToppageModel getToppageModel(String userId) {

        // 返却用モデル
        ToppageModel model = new ToppageModel();

        // 収入情報取得
        List<Incom> incomList = incomRepository.findAll();
        List<ToppageIncom> toppageIncomList = new ArrayList<>();

        // Toppageに渡す収入情報のリストを作成
        for (Incom incom : incomList) {
            ToppageIncom toppageIncom = new ToppageIncom();
            toppageIncom.setIncomMoney(incom.getIncomMoney());
            toppageIncom.setCategoryName(incom.getCateogry().getCategoryName());
            toppageIncomList.add(toppageIncom);
        }
        model.setIncomInfoList(toppageIncomList);
        return model;
    }
    
}

package com.uhablog.fuyakake.service;

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
        model.setIncomInfoList(incomRepository.getIncomInfo(userId));
        return model;
    }
    
}

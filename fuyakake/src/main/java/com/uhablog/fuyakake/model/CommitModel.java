package com.uhablog.fuyakake.model;

import java.util.List;

import com.uhablog.fuyakake.entity.Account;
import com.uhablog.fuyakake.entity.dto.ToppageBaseBalance;

import lombok.Data;

@Data
public class CommitModel {
    
    /**
     * エラーかどうか
     */
    private boolean isError;

    /**
     * メッセージ
     */
    private String message; 

    /**
     * 登録後の収支情報
     */
    private List<ToppageBaseBalance> balanceList;

    /**
     * 口座情報
     */
    private List<Account> accounts;
}

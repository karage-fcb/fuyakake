package com.uhablog.fuyakake.model;

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
}

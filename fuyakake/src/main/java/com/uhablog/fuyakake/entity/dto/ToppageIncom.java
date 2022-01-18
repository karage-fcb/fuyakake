package com.uhablog.fuyakake.entity.dto;

import lombok.Data;

@Data
public class ToppageIncom {

    /**
     * 収入情報
     */
    private int incomMoney;    

    /**
     * カテゴリ名
     */
    private String categoryName;
}

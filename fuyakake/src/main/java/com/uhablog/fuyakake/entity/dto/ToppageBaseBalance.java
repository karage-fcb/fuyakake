package com.uhablog.fuyakake.entity.dto;

import lombok.Data;

@Data
public class ToppageBaseBalance {
    
    /**
     * 金額
     */
    private int price;

    /**
     * カテゴリ名
     */
    private String categoryName;
}

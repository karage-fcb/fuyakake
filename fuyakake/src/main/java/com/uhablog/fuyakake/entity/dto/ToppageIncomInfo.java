package com.uhablog.fuyakake.entity.dto;

import java.sql.Date;

import com.uhablog.fuyakake.entity.MiddleCategory;

public interface ToppageIncomInfo {

    /**
     * 収入IDの取得
     * @return
     */
    Integer getIncom_id();

    /**
     * 収入額
     * @return
     */
    int getIncom_money();

    /**
     * 日付
     * @return
     */
    Date getDate();

    /**
     * カテゴリ
     */
    MiddleCategory getCategory();
}

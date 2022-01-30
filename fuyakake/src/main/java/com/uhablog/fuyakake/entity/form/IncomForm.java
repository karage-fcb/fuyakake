package com.uhablog.fuyakake.entity.form;

import java.sql.Date;

import lombok.Data;

@Data
public class IncomForm {

    /**
     * 収入金額
     */
    private int money;

    /**
     * 口座ID
     */
    private int accountId;

    /**
     * カテゴリID
     */
    private int categoryId;

    /**
     * メモ
     */
    private String memo;

    /**
     * 日付
     */
    private Date date;
}
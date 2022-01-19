package com.uhablog.fuyakake.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="self_investment")
@Data
public class SelfInvestment {

    /**
     * 自己投資ID
     */
    @Id
    @GeneratedValue
    private Integer selfInvestmentId;

    /**
     * 自己投資金額
     */
    private int selfInvestmentMoney;

    /**
     * 口座ID
     */
    private int accountId;

    /**
     * ユーザーID
     */
    private String userId;

    /**
     * カテゴリID
     */
    @ManyToOne
    @JoinColumn(name="cateogry_id")
    private MiddleCategory cateogry;

    /**
     * メモ
     */
    private String memo;

    /**
     * 日付
     */
    private Date date;

    /**
     * 登録日
     */
    private Timestamp createDate;

    /**
     * 登録者
     */
    private String createUser;

    /**
     * 更新日
     */
    private Timestamp updateDate;

    /**
     * 更新者
     */
    private String updateUser;

    /**
     * バージョン
     */
    private int version;

    /**
     * 削除フラグ
     */
    private boolean deleteFlag;    
}

package com.uhablog.fuyakake.entity;

import java.security.Timestamp;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="accounts")
@Data
public class Account {

    /**
     * 口座ID
     */
    @Id
    @GeneratedValue
    private Integer accountId;

    /**
     * 口座名
     */
    private String accountName;

    /**
     * 資産額
     */
    private int assetAmmount;

    /**
     * 分類タイプ
     * 1:現金 / 2:金融機関
     */
    private String categoryType;

    /**
     * ユーザーID
     */
    private String userId;

    /**
     * 登録日
     */
    private Date createDate;

    /**
     * 登録者
     */
    private String createUser;

    /**
     * 更新日
     */
    private Date updateDate;

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

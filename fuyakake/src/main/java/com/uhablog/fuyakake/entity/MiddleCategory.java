package com.uhablog.fuyakake.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="middle_category_master")
@Data
public class MiddleCategory {
    
    /**
     * カテゴリID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 大カテゴリID
     */
    @ManyToOne
    @JoinColumn(name = "big_category_id", foreignKey = @ForeignKey(name="middle_category_master_big_category_id_fkey"))
    private BigCagegory bigCagegory;

    /**
     * カテゴリ名
     */
    private String categoryName;

    /**
     * ユーザーID
     */
    private String userId;

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

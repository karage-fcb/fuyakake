package com.uhablog.fuyakake.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="user_master")
@Data
public class UserMaster {
    
    /**
     * ユーザーID
     */
    @Id
    private String userId;

    /**
     * パスワード
     */
    private String password;

    /**
     * ユーザー名
     */
    private String userName;

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

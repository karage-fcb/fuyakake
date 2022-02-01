package com.uhablog.fuyakake.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="big_category_master")
@Data
public class BigCategory {
    
    /**
     * カテゴリID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * カテゴリ名
     */
    private String categoryName;

    /**
     * 中カテゴリのリスト
     */
    // @OneToMany(mappedBy = "bigCategory")
    @OneToMany
    private List<MiddleCategory> middleCategoryList;
}

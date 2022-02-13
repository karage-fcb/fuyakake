package com.uhablog.fuyakake.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="big_category_master")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
    @OneToMany(mappedBy = "bigCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @OneToMany
    private List<MiddleCategory> middleCategories;
}

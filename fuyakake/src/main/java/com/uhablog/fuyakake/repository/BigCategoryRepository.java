package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.BigCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 大カテゴリマスタリポジトリクラス
 */
@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer>{
    
    // public List<BigCategory> getBigCategory(@Param("userId")String userId);
}

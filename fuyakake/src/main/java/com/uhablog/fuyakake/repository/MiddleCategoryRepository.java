package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.MiddleCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MiddleCategoryRepository extends JpaRepository<MiddleCategory, Integer>{
    
    /**
     * カテゴリー情報を取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  middle_category_master mc "
        + " WHERE "
        + "  user_id = :userId "
        + " OR "
        + "  user_id = 'guests' "
        , nativeQuery = true
    )
    public List<MiddleCategory> getCategory(@Param("userId")String userId);
}

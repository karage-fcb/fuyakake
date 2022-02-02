package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.BigCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 大カテゴリマスタリポジトリクラス
 */
@Repository
public interface BigCategoryRepository extends JpaRepository<BigCategory, Integer>{
    
    /**
     * 消費のカテゴリを取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  big_category_master bcm "
        + " INNER JOIN "
        + "  middle_category_master mcm "
        + " ON "
        + "  mcm.big_category_id = bcm.category_id "
        + " WHERE "
        + "  (mcm.user_id = 'guest' "
        + " OR "
        + "  mcm.user_id = :userId) "
        + " AND "
        + "  bcm.category_id != 1 "
        + " AND "
        + "  bcm.category_id != 2 "
        + " AND "
        + "  bcm.category_id != 3 ",
        nativeQuery = true
    )
    public List<BigCategory> getBigCategory(@Param("userId")String userId);
}

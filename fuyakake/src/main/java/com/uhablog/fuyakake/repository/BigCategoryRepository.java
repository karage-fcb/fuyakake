package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.common.Constant;
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
        + " SELECT DISTINCT "
        + "  * "
        + " FROM "
        + "  big_category_master bcm "
        + " INNER JOIN "
        + "  middle_category_master mcm "
        + " ON "
        + "  mcm.big_category_id = bcm.category_id "
        + " WHERE "
        + "  (mcm.user_id = 'guests' "
        + " OR "
        + "  mcm.user_id = :userId) "
        + " AND "
        + "  bcm.category_id != " + Constant.INCOM_BIG_CATEGORY_ID
        + " AND "
        + "  bcm.category_id != " + Constant.INVESTMENT_BIG_CATEGORY_ID
        + " AND "
        + "  bcm.category_id != " + Constant.SELF_INVESTMENT_BIG_CATEGORY_ID
        + " ORDER BY "
        + "  bcm.category_id "
        ,nativeQuery = true
    )
    public List<BigCategory> getBigCategory(@Param("userId")String userId);

    // @Query(value = ""
    //     + " SELECT DISTINCT "
    //     + "  b "
    //     + " FROM "
    //     + "  BigCategory b "
    //     + " INNER JOIN FETCH "
    //     + "  b.middleCategories "
    //     + " WHERE "
    //     + " EXIST (SELECT 1 FROM "
    //     + "  MiddleCategory m "
    //     + " WHERE  "
    //     + "  m.bigCategory = b "
    //     + " AND "
    //     + "  (m.user_id = 'guests' "
    //     + " OR "
    //     + "  m.user_id = :userId)) "
    //     + " AND "
    //     + "  b.category_id != " + Constant.INCOM_BIG_CATEGORY_ID
    //     + " AND "
    //     + "  b.category_id != " + Constant.INVESTMENT_BIG_CATEGORY_ID
    //     + " AND "
    //     + "  b.category_id != " + Constant.SELF_INVESTMENT_BIG_CATEGORY_ID
    // )
    // public List<BigCategory> getBigCategory(@Param("userId")String userId);

    /**
     * 指定されたIDのカテゴリを取得する
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
        + "  (mcm.user_id = 'guests' "
        + " OR "
        + "  mcm.user_id = :userId) "
        + " AND "
        + "  bcm.category_id = :categoryId "
        , nativeQuery = true
    )
    public BigCategory getOneCategory(
        @Param("userId")String userId,
        @Param("categoryId")int categoryId
    );
}
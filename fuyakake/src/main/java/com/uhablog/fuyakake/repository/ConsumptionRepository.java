package com.uhablog.fuyakake.repository;

import java.sql.Date;
import java.util.List;

import com.uhablog.fuyakake.entity.Consumption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    // Topページに表示するログインユーザーの収入情報を取得する
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  consumption "
        + " WHERE "
        + "  consumption.user_id = :userId"
        + " AND "
        + "  consumption.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate "
        + " ORDER BY consumption.date desc "
        + " LIMIT "
        + "  3 ",
        nativeQuery = true)
    public List<Consumption> getConsumption(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    );

    /**
     * 対象月の合計消費金額を取得する
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  SUM(consumption_money) "
        + " FROM "
        + "  consumption "
        + " WHERE "
        + "  consumption.user_id = :userId "
        + " AND "
        + "  consumption.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate ",
        nativeQuery = true
    )
    public int getTotalConsumption(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    );


    // TODO 次ここから
    @Query(value = ""
        + " INSERT INTO "
        + "  consumption "
        + "  ( "
        , nativeQuery = true
    )
    public int insertConsumption(
    );
}

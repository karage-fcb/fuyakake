package com.uhablog.fuyakake.repository;

import java.sql.Date;
import java.util.List;


import com.uhablog.fuyakake.entity.Consumption;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 消費情報登録
     * 
     * @param consumptionMoney
     * @param accountId
     * @param userId
     * @param categoryId
     * @param memo
     * @param date
     * @return
     */
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = ""
        + " INSERT INTO consumption("
        + "  consumption_money, "
        + "  account_id, "
        + "  user_id, "
        + "  category_id, "
        + "  memo, "
        + "  date, "
        + "  create_date, "
        + "  create_user, "
        + "  update_date, "
        + "  update_user, "
        + "  version, "
        + "  delete_flag "
        + " )VALUES( "
        + "  :consumptionMoney, "
        + "  :accountId, "
        + "  :userId, "
        + "  :categoryId, "
        + "  :memo, "
        + "  :date, "
        + "  current_timestamp, "
        + "  :userId, "
        + "  current_timestamp, "
        + "  :userId, "
        + "  1, "
        + "  'false') "
        , nativeQuery = true
    )
    public int insertConsumption(
        @Param("consumptionMoney")int consumptionMoney,
        @Param("accountId")int accountId,
        @Param("userId")String userId,
        @Param("categoryId")int categoryId,
        @Param("memo")String memo,
        @Param("date")Date date
    );
}

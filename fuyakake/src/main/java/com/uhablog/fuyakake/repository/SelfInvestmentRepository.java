package com.uhablog.fuyakake.repository;

import java.sql.Date;
import java.util.List;

import com.uhablog.fuyakake.entity.SelfInvestment;

import org.springframework.aop.AopInvocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfInvestmentRepository extends JpaRepository<SelfInvestment, Integer> {

    /**
     * 指定された年月の自己投資情報を取得する
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  self_investment "
        + " WHERE "
        + "  self_investment.user_id = :userId "
        + " AND "
        + "  self_investment.delete_flag = false"
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate "
        + " ORDER BY self_investment.date desc "
        + " LIMIT "
        + "  3 "
        ,nativeQuery = true
    )
    public List<SelfInvestment> getSelfInvestment(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    );
    
    /**
     * 対象の合計自己投資金額を取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  SUM(investment_money) "
        + " FROM "
        + "  self_investment "
        + " WHERE "
        + "  self_investment.user_id = :userId "
        + " AND "
        + "  self_investment.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate ",
        nativeQuery = true
    )
    public int getTotalSelfInvestment(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    )throws AopInvocationException;
}

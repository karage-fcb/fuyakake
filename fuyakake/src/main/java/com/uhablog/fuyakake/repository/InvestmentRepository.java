package com.uhablog.fuyakake.repository;

import java.sql.Date;
import java.util.List;

import com.uhablog.fuyakake.entity.Investment;

import org.springframework.aop.AopInvocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    
    /**
     * 指定された年月の投資情報を取得する
     * @param userId
     * @param startDate
     * @param endDate
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  investment "
        + " WHERE "
        + "  investment.user_id = :userId "
        + " AND "
        + "  investment.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate "
        + " ORDER BY investment.date desc "
        + " LIMIT "
        + "  3 ",
        nativeQuery = true
    )
    public List<Investment> getInvestment(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    );

    /**
     * 対象の合計投資金額を取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  SUM(investment_money) "
        + " FROM "
        + "  investment "
        + " WHERE "
        + "  investment.user_id = :userId "
        + " AND "
        + "  investment.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate ",
        nativeQuery = true
    )
    public int getTotalInvestment(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    )throws AopInvocationException;
}

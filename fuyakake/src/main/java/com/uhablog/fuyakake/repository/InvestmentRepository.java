package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.Investment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  investment "
        + " WHERE "
        + "  investment.user_id = :userId "
        + " AND "
        + "  investment.delete_flag = false ",
        nativeQuery = true
        )
    public List<Investment> getInvestment(@Param("userId")String userId);

    @Query(value = ""
        + " SELECT "
        + "  SUM(investment_money) "
        + " FROM "
        + "  investment "
        + " WHERE "
        + "  investment.user_id = :userId "
        + " AND "
        + "  investment.delete_flag = false ",
        nativeQuery = true
    )
    public int getTotalInvestment(@Param("userId")String userId);
}

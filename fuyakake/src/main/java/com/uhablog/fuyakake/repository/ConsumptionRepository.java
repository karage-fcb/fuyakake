package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.Consumption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, Integer> {

    // Topページに表示するログインユーザーの収入情報を取得する
    // TODO: 期間の指定、取得数の指定を行う 
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  consumption "
        + " WHERE "
        + "  consumption.user_id = :userId"
        + " AND "
        + "  consumption.delete_flag = false ",
        nativeQuery = true)
    public List<Consumption> getConsumption(@Param("userId")String userId);

    @Query(value = ""
        + " SELECT "
        + "  SUM(consumption_money) "
        + " FROM "
        + "  consumption "
        + " WHERE "
        + "  consumption.user_id = :userId "
        + " AND "
        + "  consumption.delete_flag = false ",
        nativeQuery = true
    )
    public int getTotalConsumption(@Param("userId")String userId);
}

package com.uhablog.fuyakake.repository;

import java.sql.Date;
import java.util.List;


import com.uhablog.fuyakake.entity.Incom;

import org.springframework.aop.AopInvocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IncomRepository extends JpaRepository<Incom, Integer> {
    
    // Topページに表示するログインユーザーの収入情報を取得する
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  incom "
        + " WHERE "
        + "  incom.user_id = :userId"
        + " AND "
        + "  incom.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate "
        + " ORDER BY incom.date desc "
        + " LIMIT "
        + "  3 ",
        nativeQuery = true)
    public List<Incom> getIncom(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    );


    /**
     * 収入の合計値を取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  SUM(incom_money) "
        + " FROM "
        + "  incom "
        + " WHERE "
        + "  incom.user_id = :userId "
        + " AND "
        + "  incom.delete_flag = false "
        + " AND "
        + "  date BETWEEN "
        + "   :startDate "
        + "  AND "
        + "   :endDate ",
        nativeQuery = true
    )
    public int getTotalIncom(
        @Param("userId")String userId,
        @Param("startDate")Date startDate,
        @Param("endDate")Date endDate
    )throws AopInvocationException;


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(
        value = ""
        + " INSERT INTO incom( "
        + "  incom_money, "
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
        + "  :incomMoney, "
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
        + "  'false', "
        , nativeQuery = true
    )
    public int insertIncom(
        @Param("incomMoney")int incomMoney,
        @Param("accountId")int accountId,
        @Param("userId")String userId,
        @Param("categoryId")int categoryId,
        @Param("memo")String memo,
        @Param("date")Date date
    );
}

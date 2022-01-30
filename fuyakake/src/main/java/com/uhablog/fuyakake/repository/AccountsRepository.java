package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.Account;

import org.springframework.aop.AopInvocationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Integer>{

    /**
     * 口座情報を3つ取得する
     * @param userId
     * @return
     */
    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  accounts "
        + " WHERE "
        + "  accounts.user_id = :userId "
        + " AND "
        + "  accounts.delete_flag = false "
        + " LIMIT "
        + "  3 "
        , nativeQuery = true
    )
    public List<Account> getAccounts(@Param("userId")String userId);

    /**
     * 合計資産を取得する
     * @param userId
     * @return
     * @throws AopInvocationException
     */
    @Query(value = ""
        + " SELECT "
        + "  SUM(asset_ammount) "
        + " FROM "
        + "  accounts "
        + " WHERE "
        + "  accounts.user_id = :userId "
        + " AND "
        + "  accounts.delete_flag = false "
        , nativeQuery = true
    )
    public int getTotalAsset(
        @Param("userId")String userId
    )throws AopInvocationException;
    
}

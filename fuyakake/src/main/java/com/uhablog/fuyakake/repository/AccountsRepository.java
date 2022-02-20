package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.Account;

import org.springframework.aop.AopInvocationException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
        + " ORDER BY "
        + "  accounts.update_date "
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
        + "  SUM(asset_amount) "
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
    
    /**
     * 全ての口座を取得する
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
        + " ORDER BY "
        + "  accounts.account_id "
        , nativeQuery = true
    )
    public List<Account> getAllAccount(@Param("userId")String userId);

    @Query(value = ""
        + " SELECT "
        + "  * "
        + " FROM "
        + "  accounts "
        + " WHERE "
        + "  accounts.account_id = :accountId "
        ,nativeQuery = true
    )
    public Account getOneAccount(
        @Param("accountId")int accountId
    );

    /**
     * 口座情報の更新
     * 上昇
     * @param accountId
     * @param assetAmount
     * @return
     */
    @Modifying
    @Query(value = ""
        + " UPDATE "
        + "  accounts "
        + " SET "
        + "  asset_amount = asset_amount + :assetAmount "
        + " WHERE "
        + "  account_id = :accountId "
        , nativeQuery = true
    )
    public int updateIncomAmount(
        @Param("accountId")Integer accountId,
        @Param("assetAmount")int assetAmount
    );

    /**
     * 口座から指定金額を差し引く
     * @param accountId
     * @param assetAmount
     * @return
     */
    @Modifying
    @Query(value = ""
        + " UPDATE "
        + "  accounts "
        + " SET "
        + "  asset_amount = asset_amount - :assetAmount "
        + " WHERE "
        + "  account_id = :accountId "
        , nativeQuery = true
    )
    public int updateConsumptionAmount(
        @Param("accountId")Integer accountId,
        @Param("assetAmount")int assetAmount
    );
}

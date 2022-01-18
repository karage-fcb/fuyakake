package com.uhablog.fuyakake.repository;

import java.util.List;

import com.uhablog.fuyakake.entity.Incom;
import com.uhablog.fuyakake.entity.dto.ToppageIncomInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomRepository extends JpaRepository<Incom, Integer> {
    
    /**
     * 対象月の収入情報取得
     */
    // @Query(value=" SELECT "
    //     +  "  incom.incom_id,"
    //     +  "  incom.incom_money, "
    //     +  "  midCategory.category_id, "
    //     +  "  midCategory.category_name, "
    //     +  "  incom.date "
    //     +  " FROM "
    //     +  "  incom "
    //     +  " LEFT JOIN "
    //     +  "  middle_category_master midCategory "
    //     +  " ON "
    //     +  "  incom.category_id = midCategory.category_id "
    //     +  " WHERE "
    //     +  "  incom.user_id = :userId "
    //     +  " AND "
    //     +  "  incom.delete_flag = 'false' ",
    //     nativeQuery = true
    // )
    @Query(value = " SELECT "
        +  "  incom.incom_id,"
        +  "  incom.incom_money, "
        +  "  incom.date "
        +  " FROM "
        +  "  incom "
        +  " WHERE "
        +  "  incom.user_id = :userId ",
        nativeQuery = true
    )
    public List<ToppageIncomInfo> getIncomInfo(@Param("userId")String userId);

    // public List<Incom> getIncom(@Param("userId")String userId);
}

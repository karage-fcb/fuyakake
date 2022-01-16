package com.uhablog.fuyakake.repository;

import com.uhablog.fuyakake.entity.UserMaster;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<UserMaster, String> {
}

package com.uhablog.fuyakake.repository;

import com.uhablog.fuyakake.entity.Test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends CrudRepository<Test, Long> {
}

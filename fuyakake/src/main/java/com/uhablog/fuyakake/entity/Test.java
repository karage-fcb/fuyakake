package com.uhablog.fuyakake.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    protected Test() {}

    public Test(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("{id:%d,name:%s", id, name);
    }
}

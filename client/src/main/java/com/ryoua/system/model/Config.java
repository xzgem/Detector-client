package com.ryoua.system.model;

import lombok.Data;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Entity
@Proxy(lazy = false)
public class Config {
    @Id
    private Integer id;
    private String mid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}

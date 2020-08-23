package com.ryoua.system.repository;

import com.ryoua.system.model.Config;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/23
 **/
@Repository
public interface ConfigRepository extends CrudRepository<Config, Integer> {
}

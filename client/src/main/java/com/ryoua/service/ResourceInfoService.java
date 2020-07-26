package com.ryoua.service;

import com.ryoua.model.LoadInfo;
import org.springframework.stereotype.Service;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/25
 **/
@Service
public class ResourceInfoService {
    public LoadInfo getResourceInfo() {
        LoadInfo loadInfo = new LoadInfo();

        return loadInfo;
    }

}

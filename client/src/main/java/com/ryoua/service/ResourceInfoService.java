package com.ryoua.service;

import com.ryoua.model.ResourceInfo;
import org.springframework.stereotype.Service;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/25
 **/
@Service
public class ResourceInfoService {
    public ResourceInfo getResourceInfo() {
        ResourceInfo resourceInfo = new ResourceInfo();

        return resourceInfo;
    }

}

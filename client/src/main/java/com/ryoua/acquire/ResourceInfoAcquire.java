package com.ryoua.acquire;

import com.ryoua.model.ResourceInfo;
import org.springframework.stereotype.Service;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/3
 **/
@Service
public interface ResourceInfoAcquire {
    ResourceInfo getResourceInfo();
}

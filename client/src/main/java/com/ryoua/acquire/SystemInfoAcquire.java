package com.ryoua.acquire;

import com.ryoua.model.SystemInfo;
import org.springframework.stereotype.Service;

/**
 * @Author: RyouA
 * @Date: 2020/5/23 - 7:43 下午
 **/
@Service
public interface SystemInfoAcquire {
    SystemInfo getSystemInfo();
}

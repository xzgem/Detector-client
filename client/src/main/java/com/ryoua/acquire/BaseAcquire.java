package com.ryoua.acquire;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.Formatter;
import java.util.Properties;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/30
 **/
public class BaseAcquire {
    static final oshi.SystemInfo si = new SystemInfo();

    static final HardwareAbstractionLayer hal = si.getHardware();

    static final OperatingSystem os = si.getOperatingSystem();

    static final Formatter formatter = new Formatter();

    static final Properties properties = System.getProperties();
}

package com.ryoua.system.service;

import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

import java.util.Formatter;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/16
 **/
public class BaseService {
    static final oshi.SystemInfo si = new oshi.SystemInfo();

    static final HardwareAbstractionLayer hal = si.getHardware();

    static final OperatingSystem os = si.getOperatingSystem();

    static final Formatter formatter = new Formatter();
}

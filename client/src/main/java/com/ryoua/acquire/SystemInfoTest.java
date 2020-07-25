package com.ryoua.acquire;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/24
 **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.*;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.LsofUtil;
import oshi.util.Util;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

/**
 * The Class SystemInfoTest.
 *
 * @author dblock[at]dblock[dot]org
 */
public class SystemInfoTest {
    private static final SystemInfo si = new SystemInfo();
    private static final HardwareAbstractionLayer hal = si.getHardware();
    private static final OperatingSystem os = si.getOperatingSystem();
    private static final Formatter formatter = new Formatter();




    public static String traffic(NetworkIF[] networkIFs) {

        StringBuilder stringBuilder = new StringBuilder();
        for (NetworkIF net : networkIFs) {
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;

            stringBuilder.append("流量: 发送 " + (hasData ? net.getPacketsRecv() + "_packets " : "?") +
                    (hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?") +
                    (hasData ? " (" + net.getInErrors() + " err) " : "") +
                    "    接收 " +
                    (hasData ? net.getPacketsSent() + "_packets" : "?") +
                    (hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?") +
                    (hasData ? " (" + net.getOutErrors() + " err)" : ""));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
    }
}

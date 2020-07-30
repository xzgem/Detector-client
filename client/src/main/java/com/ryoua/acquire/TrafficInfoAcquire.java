package com.ryoua.acquire;

import oshi.hardware.NetworkIF;

/**
 * * @Author: RyouA
 * * @Date: 2020/7/30
 **/
public class TrafficInfoAcquire extends BaseAcquire {
    public static String traffic() {
        NetworkIF[] networkIFs = hal.getNetworkIFs();
        StringBuilder stringBuilder = new StringBuilder();
        for (NetworkIF net : networkIFs) {
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;

            stringBuilder.append("流量: 发送 " + (hasData ? net.getPacketsRecv() + "_packets " : "?") +
                    (hasData ? oshi.util.FormatUtil.formatBytes(net.getBytesRecv()) : "?") +
                    (hasData ? " (" + net.getInErrors() + " err) " : "") +
                    "    接收 " +
                    (hasData ? net.getPacketsSent() + "_packets " : "?") +
                    (hasData ? oshi.util.FormatUtil.formatBytes(net.getBytesSent()) : "?") +
                    (hasData ? " (" + net.getOutErrors() + " err)" : ""));
        }
        return stringBuilder.toString();
    }
}

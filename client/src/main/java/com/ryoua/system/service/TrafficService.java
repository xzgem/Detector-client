package com.ryoua.system.service;

import com.ryoua.system.config.Constants;
import com.ryoua.system.model.Traffic;
import com.ryoua.system.utils.TimeUtil;
import org.springframework.stereotype.Service;
import oshi.hardware.NetworkIF;
import oshi.util.FormatUtil;

import java.util.*;

/**
 * * @Author: RyouA
 * * @Date: 2020/8/15
 **/
@Service
public class TrafficService extends BaseService{
    static Map<String, NetworkIF> lastTraffic = new HashMap<>();

    static {
        NetworkIF[] networkIFs = hal.getNetworkIFs();
        for (NetworkIF net : networkIFs) {
            lastTraffic.put(net.getName(), net);
        }
    }

    public List<Traffic> getTraffic() {
        NetworkIF[] networkIFs = hal.getNetworkIFs();
        List<Traffic> list = new ArrayList<>();
        for (NetworkIF net : networkIFs) {
            NetworkIF last = lastTraffic.get(net.getName());
            Traffic traffic = new Traffic();

            traffic.setNetworkInterfaceName(net.getName());
            traffic.setIpv4(net.getIPv4addr()[0]);
            traffic.setIpv6(net.getIPv6addr()[0]);
            traffic.setCreateTime(TimeUtil.getNowTime());
            traffic.setCreateTimeMills(TimeUtil.getNowTimeMills());
            traffic.setMid(Constants.mid);
            traffic.setReceivePackets(net.getPacketsRecv() - last.getPacketsRecv());
            traffic.setSendPackets(net.getPacketsSent() - last.getPacketsSent());
            traffic.setReceiveSpeed(net.getBytesRecv() - last.getBytesRecv());
            traffic.setReceiveSpeedStr(FormatUtil.formatBytes(net.getBytesRecv() - last.getBytesRecv()));
            traffic.setSendSpeed(net.getBytesSent() - last.getBytesSent());
            traffic.setSendSpeedStr(FormatUtil.formatBytes(net.getBytesSent() - last.getBytesSent()));
            lastTraffic.replace(net.getName(), net);

            list.add(traffic);
        }
        return list;
    }
}

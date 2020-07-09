package com.ryoua.acquire;

import com.ryoua.model.SystemInfo;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

/**
 * 获取系统的基本信息
 * @Author: RyouA
 * @Date: 2020/5/23 - 7:46 下午
 **/
@Service
public class BaseSystemInfoAcquire implements SystemInfoAcquire {

    /**
     * 得到计算机的ip地址和mac地址
     */
    public void getNetConfig(SystemInfo systemInfo) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            if (mac != null) {
                for (int i = 0; i < mac.length; i++) {
                    sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                            (i < mac.length - 1) ? "-" : "").toString();
                }
            }
            systemInfo.setIp(sIP);
            systemInfo.setMac(sMAC);
            systemInfo.setHost(address.getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到计算机的操作系统名称,操作系统版本
     * @param systemInfo
     */
    public void getOsConfig(SystemInfo systemInfo) {
        try {
            Properties props = System.getProperties();
            systemInfo.setOsName(props.getProperty("os.name"));
            systemInfo.setOsVersion(props.getProperty("os.version"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 其它的一些东西,会有用到的时候的
     * @param systemInfo
     */
    public void getOtherInfo(SystemInfo systemInfo) {
        Properties props = System.getProperties();
        systemInfo.setJavaVersion(props.getProperty("java.version"));
        systemInfo.setUsername(props.getProperty("user.name"));
    }

    public SystemInfo getSystemInfo() {
        SystemInfo systemInfo = new SystemInfo();
        getNetConfig(systemInfo);
        getOsConfig(systemInfo);
        getOtherInfo(systemInfo);
        return systemInfo;
    }
}

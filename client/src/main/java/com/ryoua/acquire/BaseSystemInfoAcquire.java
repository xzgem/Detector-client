package com.ryoua.acquire;

import com.ryoua.model.SystemInfo;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Formatter;
import java.util.Locale;
import java.util.Properties;

/**
 * 获取系统的基本信息
 * @Author: RyouA
 * @Date: 2020/5/23 - 7:46 下午
 **/
@Service
public class BaseSystemInfoAcquire {
    private static final Formatter formatter = new Formatter();

    private static final Properties properties = System.getProperties();

    public static String getIp() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostAddress();
    }

    public static String getMac() throws UnknownHostException, SocketException {
        InetAddress address = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(address);
        byte[] mac = ni.getHardwareAddress();
        String sMAC = "";
        if (mac != null) {
            for (int i = 0; i < mac.length; i++) {
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : "").toString();
            }
        }
        return sMAC;
    }

    public static String getHost() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return address.getHostName();
    }

    public static String getOsName() {
        return properties.getProperty("os.name");
    }

    public static String getOsVersion() {
        return properties.getProperty("os.version");
    }
}

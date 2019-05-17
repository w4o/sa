package com.github.w4o.sa.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author frank
 * @date 2019-05-17
 */
public class IpUtil {

    private static final String DEFAULT_REMOTE_ADDR = "127.0.0.1";

    private static final String UNKNOWN_REMOTE_ADDR = "unknown";

    private static final char MULTIPLE_IP_DELIMITER = ',';

    /**
     * 获取用户IP
     *
     * @param request HttpServletRequest
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN_REMOTE_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_REMOTE_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN_REMOTE_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (DEFAULT_REMOTE_ADDR.equals(ip)) {
                //根据网卡取本机配置的IP
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inetAddress != null) {
                    ip = inetAddress.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.indexOf(MULTIPLE_IP_DELIMITER) > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }


}

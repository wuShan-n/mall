package com.mall.gateway.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.net.InetSocketAddress;

/**
 * IP工具类
 */
public class IpUtils {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    private static final String COMMA = ",";

    /**
     * 获取客户端IP地址
     */
    public static String getClientIp(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        
        String ip = headers.getFirst("X-Forwarded-For");
        if (isValidIp(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP
            if (ip.contains(COMMA)) {
                ip = ip.split(COMMA)[0];
            }
            return ip;
        }
        
        ip = headers.getFirst("X-Real-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = headers.getFirst("Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = headers.getFirst("WL-Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = headers.getFirst("HTTP_CLIENT_IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 获取直接连接的IP地址
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        if (remoteAddress != null) {
            ip = remoteAddress.getAddress().getHostAddress();
            // 如果是IPv6的localhost，转换为IPv4
            if (LOCALHOST_IPV6.equals(ip)) {
                ip = LOCALHOST_IP;
            }
        }
        
        return ip;
    }

    private static boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !UNKNOWN.equalsIgnoreCase(ip);
    }
}
package com.zy.accesslog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IPFetcher {
    private static final String FORWARDED_FOR_HEADER = "X-Forwarded-For";
    private static Logger logger = LoggerFactory.getLogger(IPFetcher.class);

    public static String getIPAddress(HttpServletRequest request) {
        String userAddress = StringUtils.isEmpty(request.getHeader(FORWARDED_FOR_HEADER)) ?
                request.getRemoteHost() :
                request.getHeader(FORWARDED_FOR_HEADER);

        if (StringUtils.hasText(userAddress) && userAddress.indexOf(',') > 0) {
            userAddress = userAddress.substring(0, userAddress.indexOf(','));
        }

        return userAddress;
    }

    /**
     * 获取IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            logger.error("IPUtils ERROR ", e);
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }
}

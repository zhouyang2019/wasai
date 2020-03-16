package com.zy.accesslog;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Pattern;

@Component
public class AccessLogBuilder {

    public String buildAccessLog(HttpServletRequest request) {
        StringBuilder accessLog = new StringBuilder();
        build(accessLog, request.getRemoteHost());
        build(accessLog, request.getHeader("X-Forwarded-For"));
        build(accessLog, request.getRemoteUser());
        build(accessLog, request.getMethod());
        build(accessLog, buildRequestPath(request));
        build(accessLog, request.getHeader("Referer"));
        build(accessLog, request.getHeader("User-Agent"));
        build(accessLog, IPFetcher.getIpAddr(request));
        return accessLog.toString().substring(1);
    }

    public String buildResponseLog(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder responseLog = new StringBuilder();
        Instant enterTime = (Instant) request.getAttribute("trace-enter-time");
        if (enterTime != null) {
            build(responseLog, String.valueOf(Duration.between(enterTime, Instant.now()).getNano()));
        }
        build(responseLog, Integer.toString(response.getStatus()));
        build(responseLog, response.getHeader("Content-Length"));
        build(responseLog, response.getHeader("ETag"));
        return responseLog.toString().substring(1);
    }

    private void build(StringBuilder sb, String str) {
        sb.append(" ");
        if (StringUtils.isEmpty(str)) {
            sb.append("-");
        } else if (Pattern.compile("[ \t\n\r]").matcher(str).find()) {
            sb.append("\"");
            sb.append(str.replace("\"", "\"\""));
            sb.append("\"");
        } else {
            sb.append(str);
        }
    }

    private String buildRequestPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            path += "?" + queryString;
        }
        return path;
    }

}

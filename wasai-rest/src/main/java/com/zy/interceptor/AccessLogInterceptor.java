package com.zy.interceptor;

import com.zy.accesslog.AccessLogBuilder;
import com.zy.mdc.MDCKey;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Component
public class AccessLogInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

    @Autowired
    private AccessLogBuilder accessLogBuilder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        buildMDC();
        request.setAttribute("trace-enter-time", Instant.now());
        logger.info(accessLogBuilder.buildAccessLog(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info(accessLogBuilder.buildResponseLog(request, response));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        teardownMDC();
    }

    private void buildMDC() {
        MDC.put(MDCKey.DIAGNOSTIC_ID.name(), UUID.randomUUID().toString());
    }

    private void teardownMDC() {
        Arrays.asList(MDCKey.values()).forEach(key -> MDC.remove(key.name()));
    }
}

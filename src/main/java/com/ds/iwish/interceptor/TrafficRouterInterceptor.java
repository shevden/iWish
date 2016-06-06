package com.ds.iwish.interceptor;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TrafficRouterInterceptor extends HandlerInterceptorAdapter {

    public static final Logger LOG = LogManager.getLogger(TrafficRouterInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOG.debug("Checked by Traffic Router.");
        return true;
    }
}

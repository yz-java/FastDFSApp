package com.yz.filter;

import com.alibaba.fastjson.JSON;
import com.yz.util.HttpUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by yangzhao on 17/8/3.
 */
@WebFilter("/*")
public class BaseFilter implements Filter {

    private final Logger logger = LogManager.getLogger(BaseFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("客户端IP:"+ HttpUtil.getIpAddr((HttpServletRequest) servletRequest));

        Map<String, String[]> parameterMap = servletRequest.getParameterMap();

        logger.info("请求数据："+ JSON.toJSONString(parameterMap));

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }
}

package com.gtf.cloud.consumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * OpenFeign请求携带Authorization
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/7/13 16:11
 */
@Slf4j
@Configuration
public class FeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String authorization = request.getHeader("authorization");
        log.error("请求头携带的authorization为:{}", authorization);
        requestTemplate.header("authorization", authorization);
    }
}

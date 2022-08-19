package com.gtf.cloud.consumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * 用户
 * @EnableEurekaClient 使用eureka注册中使用该注解
 * @EnableDiscoveryClient 使用其他注册中心需使用该注解
 * @EnableFeignClients 开启Feign
 * @EnableHystrix  开启EnableHystrix，同时也具有@EnableCircuitBreaker的功能，因为继承了该注解
 * @EnableHystrixDashboard 开启EnableHystrix仪表盘
 * @author : GTF
 * @version : 1.0
 * @date : 2022/6/28 14:58
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class Employee02Application {
    public static void main(String[] args) {
        SpringApplication.run(Employee02Application.class, args);
    }

    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}

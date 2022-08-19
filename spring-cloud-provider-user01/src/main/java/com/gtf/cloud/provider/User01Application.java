package com.gtf.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/18 17:30
 */
@SpringBootApplication
@EnableDiscoveryClient
public class User01Application {
    public static void main(String[] args) {
        SpringApplication.run(User01Application.class, args);
    }
}

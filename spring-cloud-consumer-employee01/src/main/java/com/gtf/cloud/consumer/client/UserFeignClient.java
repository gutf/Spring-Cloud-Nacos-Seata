package com.gtf.cloud.consumer.client;

import com.gtf.cloud.common.vo.UserUpdateVo;
import com.gtf.cloud.consumer.client.hystrix.UserHystrix;
import com.gtf.cloud.consumer.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/19 10:51
 */
@FeignClient(value = "spring-cloud-user-provider",
        contextId = "spring-cloud-user-provider",
        configuration = FeignConfiguration.class,
        fallbackFactory = UserHystrix.class)
public interface UserFeignClient {

    @PatchMapping("/api/user/provider/v1/user")
    void updateUser(@RequestBody UserUpdateVo userUpdateVo);
}

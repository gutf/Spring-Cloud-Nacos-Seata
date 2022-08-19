package com.gtf.cloud.consumer.client.hystrix;

import com.gtf.cloud.common.vo.UserUpdateVo;
import com.gtf.cloud.consumer.client.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/19 10:52
 */
@Slf4j
@Component
public class UserHystrix implements FallbackFactory<UserFeignClient> {
    @Override
    public UserFeignClient create(Throwable cause) {
        return new UserFeignClient() {
            @Override
            public void updateUser(UserUpdateVo userUpdateVo) {
                log.error("User 服务降级......");
            }
        };
    }
}

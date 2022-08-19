package com.gtf.cloud.consumer.client.hystrix;

import com.gtf.cloud.common.vo.DeptUpdateQo;
import com.gtf.cloud.consumer.client.DepartmentFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 部门请求熔断器
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/6/28 16:15
 */
@Slf4j
@Component
public class DepartmentHystrix implements FallbackFactory<DepartmentFeignClient> {

    @Override
    public DepartmentFeignClient create(Throwable cause) {
        return new DepartmentFeignClient(){
            @Override
            public String getDepartment(){
                log.error("Feign DepartmentHystrix getDepartment 熔断降级");
                return "getDepartment应用熔断降级";
            }

            @Override
            public void updateDept(@RequestBody DeptUpdateQo deptUpdateQo) {
                log.error("Feign DepartmentHystrix updateDept 熔断降级");
            }
        };
    }
}

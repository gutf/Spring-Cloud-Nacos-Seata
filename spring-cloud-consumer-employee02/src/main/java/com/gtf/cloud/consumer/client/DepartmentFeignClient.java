package com.gtf.cloud.consumer.client;

import com.gtf.cloud.common.vo.DeptUpdateQo;
import com.gtf.cloud.consumer.client.hystrix.DepartmentHystrix;
import com.gtf.cloud.consumer.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 部门Feign请求
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/6/28 16:14
 */
@FeignClient(value = "spring-cloud-department-provider",
        contextId = "spring-cloud-department-provider",
        configuration = FeignConfiguration.class,
        fallbackFactory = DepartmentHystrix.class)
public interface DepartmentFeignClient {

    /**
    * 获取部门信息
    * @author GTF
    * @date 2022/6/28 17:06
    * @return java.lang.String
    */
    @GetMapping("/api/provider/v1/department")
    String getDepartment();

    @PatchMapping("/api/provider/v1/department")
    void updateDept(@RequestBody DeptUpdateQo deptUpdateQo);
}

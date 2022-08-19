package com.gtf.cloud.provider.controller;

import com.gtf.cloud.common.vo.DeptUpdateQo;
import com.gtf.cloud.provider.po.Dept;
import com.gtf.cloud.provider.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息控制器
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/6/28 15:05
 */
@Slf4j
@RestController
@RequestMapping("/v1/department")
@RefreshScope
@RequiredArgsConstructor
public class DepartmentController {

    @Value("${nacos.provider.key}")
    private String key;

    @Value("${nacos.provider.value}")
    private String value;

    @Value("${server.port}")
    private String port;

    private final DeptService deptService;

    @GetMapping
    public String getDepartment(@RequestParam Long deptId){
        return deptService.getDeptById(deptId).getDeptName();
    }

    @PatchMapping
    public void updateDept(@RequestBody DeptUpdateQo deptUpdateQo){
        deptService.updateDept(deptUpdateQo);
    }
}

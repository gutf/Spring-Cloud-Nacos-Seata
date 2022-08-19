package com.gtf.cloud.consumer.service;

import com.gtf.cloud.common.vo.EmployeeUpdateQo;
import com.gtf.cloud.consumer.po.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface EmployeeService extends IService<Employee> {

    void updateEmployee(EmployeeUpdateQo qo);

}

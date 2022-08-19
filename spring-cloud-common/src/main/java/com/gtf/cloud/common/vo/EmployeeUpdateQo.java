package com.gtf.cloud.common.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/17 17:37
 */
@Data
public class EmployeeUpdateQo {

    private Long employeeId;

    private String employeeName;

    private String employeeDesc;

    private Long deptId;

    private String deptDesc;

    private String deptName;

    private Long userId;

    private String name;

    private String password;
}

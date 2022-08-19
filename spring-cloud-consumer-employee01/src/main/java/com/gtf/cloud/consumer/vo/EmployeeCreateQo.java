package com.gtf.cloud.consumer.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/17 17:37
 */
@Data
public class EmployeeCreateQo {

    private Long deptId;

    private String employeeName;

    private String employeeDesc;
}

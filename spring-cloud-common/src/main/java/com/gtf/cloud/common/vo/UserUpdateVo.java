package com.gtf.cloud.common.vo;

import lombok.Data;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/19 10:19
 */
@Data
public class UserUpdateVo {
    private Long userId;

    private String name;

    private String password;
}

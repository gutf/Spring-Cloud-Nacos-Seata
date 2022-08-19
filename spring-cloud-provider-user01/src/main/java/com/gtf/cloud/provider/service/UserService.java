package com.gtf.cloud.provider.service;

import com.gtf.cloud.common.vo.UserUpdateVo;
import com.gtf.cloud.provider.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 * @author GTF
 */
public interface UserService extends IService<User> {

    void updateUser(UserUpdateVo userUpdateVo);

}

package com.gtf.cloud.provider.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gtf.cloud.common.vo.UserUpdateVo;
import com.gtf.cloud.provider.po.User;
import com.gtf.cloud.provider.service.UserService;
import com.gtf.cloud.provider.mapper.UserMapper;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author GTF
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final UserMapper userMapper;
    @Override
    public void updateUser(UserUpdateVo userUpdateVo) {
        String xid = RootContext.getXID();
        log.error("xid>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+xid);
        User user = new User();
        user.setId(userUpdateVo.getUserId());
        user.setName(userUpdateVo.getName());
        user.setPassword(userUpdateVo.getPassword());

        userMapper.updateById(user);
    }
}





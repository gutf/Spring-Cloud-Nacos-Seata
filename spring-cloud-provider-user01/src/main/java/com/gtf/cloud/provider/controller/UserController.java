package com.gtf.cloud.provider.controller;

import com.gtf.cloud.common.vo.UserUpdateVo;
import com.gtf.cloud.provider.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author : GTF
 * @version : 1.0
 * @date : 2022/8/19 10:18
 */
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping
    public void updateUser(@RequestBody UserUpdateVo userUpdateVo){
        userService.updateUser(userUpdateVo);
    }
}

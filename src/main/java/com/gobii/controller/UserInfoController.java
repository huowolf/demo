package com.gobii.controller;

import com.gobii.dto.UserTable;
import com.gobii.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huowolf
 * @date 2019/4/27
 * @description 用户信息管理
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getUserTable")
    public UserTable getUserTable(@RequestParam(defaultValue = "1") Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit){

        UserTable userTable = userInfoService.getUserTable(page, limit);
        return userTable;
    }
}

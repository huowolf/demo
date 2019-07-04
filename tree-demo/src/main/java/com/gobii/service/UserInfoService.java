package com.gobii.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gobii.dto.UserTable;
import com.gobii.mapper.UserinfoMapper;
import com.gobii.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huowolf
 * @date 2019/4/29
 * @description
 */
@Service
public class UserInfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    public UserTable getUserTable(Integer page, Integer limit){

        PageHelper.startPage(page,limit);

        List<Userinfo> userinfos = userinfoMapper.selectAll();

        long total = ((Page) userinfos).getTotal();

        UserTable userTable = new UserTable();
        userTable.setTotal(total);
        userTable.setRows(userinfos);

        return userTable;
    }
}

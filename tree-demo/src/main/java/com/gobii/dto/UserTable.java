package com.gobii.dto;

import com.gobii.model.Userinfo;
import lombok.Data;

import java.util.List;

/**
 * @author huowolf
 * @date 2019/4/29
 * @description 用户表单对象
 */
@Data
public class UserTable {

    private Long total;

    private List<Userinfo> rows;
}

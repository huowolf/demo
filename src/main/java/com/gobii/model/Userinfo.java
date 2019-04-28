package com.gobii.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
public class Userinfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonIgnore
    private String password;

    private String photo;

    private String phone;

    private String email;

    /**
     * 1:顾客 2：商家
     */
    @JsonIgnore
    private Integer type;

    /**
     * 用户是否可用 1：可用 0：不可用
     */
    @JsonIgnore
    private Integer status;

}
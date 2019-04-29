package com.gobii.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author huowolf
 * @date 2019/4/29
 * @description 树节点
 */
@Data
public class TreeNode {

    private Integer id;

    @JsonProperty(value = "pId")
    private Integer pId;

    private String name;

    private Boolean open;
}

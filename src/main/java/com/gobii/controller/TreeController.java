package com.gobii.controller;

import com.gobii.dto.TreeNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huowolf
 * @date 2019/4/29
 * @description
 */
@RestController
@RequestMapping("/tree")
public class TreeController {

    @PostMapping("/getTreeNodes")
    public List<TreeNode> getTreeNodes(){

        //模拟从数据库得到的返回数据
        List<TreeNode> treeNodes = new ArrayList<>();

        //一级菜单
        for (int i = 1; i <= 5; i++) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(i);
            treeNode.setPId(0);
            treeNode.setName("父菜单"+i);
            treeNode.setOpen(true);
            treeNodes.add(treeNode);

            //二级菜单
            for (int j = 1; j <= 3; j++) {
                TreeNode node = new TreeNode();
                node.setId(Integer.parseInt(i+""+j));
                node.setPId(i);
                node.setName("二级菜单"+i+""+j);
                treeNodes.add(node);
            }
        }

        return treeNodes;
    }
}

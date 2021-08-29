package com.zxk.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permission")
public class Permission {
    private Integer id;
    private String name;
    private String descritpion;
    private String url;
    private Integer pid;
}

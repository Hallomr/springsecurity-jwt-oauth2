package com.zxk.oauth2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class User {
    private Integer id;
    private String username;
    private String password;
}

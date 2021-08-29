package com.zxk.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxk.oauth2.entity.User;

public interface UserService extends IService<User> {
    User findByUsername(String username);
}

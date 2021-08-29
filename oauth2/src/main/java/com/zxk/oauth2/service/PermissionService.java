package com.zxk.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zxk.oauth2.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> findByUserId(Integer id);
}

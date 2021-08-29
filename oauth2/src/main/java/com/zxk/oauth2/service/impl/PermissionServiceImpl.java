package com.zxk.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxk.oauth2.entity.Permission;
import com.zxk.oauth2.mapper.PermissionMapper;
import com.zxk.oauth2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserId(Integer id) {
        return permissionMapper.findByUserId(id);
    }
}

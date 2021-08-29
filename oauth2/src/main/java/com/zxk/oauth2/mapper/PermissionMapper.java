package com.zxk.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxk.oauth2.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Permission> findByUserId(Integer id);
}

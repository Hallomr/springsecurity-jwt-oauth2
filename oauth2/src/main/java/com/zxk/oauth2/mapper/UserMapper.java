package com.zxk.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxk.oauth2.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

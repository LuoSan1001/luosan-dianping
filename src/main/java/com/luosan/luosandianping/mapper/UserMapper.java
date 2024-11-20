package com.luosan.luosandianping.mapper;

import com.luosan.luosandianping.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User queryByPhone(String phone);

    void createUser(User user);
}

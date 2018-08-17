package com.guya2.repository;


import com.guya2.generate.common.domain.User;
import com.guya2.generate.common.domain.UserExample;

import java.util.List;


public interface UserRepository {

    User selectByPrimaryKey(Long id);

    List<User> selectByExample(UserExample example);

    int insert(User record);

}

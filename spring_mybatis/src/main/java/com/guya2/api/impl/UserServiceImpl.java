package com.guya2.api.impl;

import com.guya2.common.req.LoginRequest;
import com.guya2.common.req.QueryByIdListRequest;
import com.guya2.common.req.QueryByIdRequest;
import com.guya2.common.response.LoginResponse;
import com.guya2.common.response.BaseResponse;
import com.guya2.common.response.QueryByIdListResponse;
import com.guya2.common.response.QueryByIdResponse;
import com.guya2.contract.UserService;
import com.guya2.generate.common.domain.User;
import com.guya2.generate.common.domain.UserExample;
import com.guya2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponse login(LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(request.getUsername());
        loginResponse.setPassword(request.getPassword());
        return BaseResponse.newSuccResponse().result(loginResponse).build();
    }

    @Override
    public BaseResponse queryById(QueryByIdRequest request) {
        QueryByIdResponse queryByIdResponse = new QueryByIdResponse();
        User user = userRepository.selectByPrimaryKey(request.getId());
        if (user == null) {
            return BaseResponse.newFailResponse().result(queryByIdResponse).build();
        }
        queryByIdResponse.setUsername(user.getUsername());
        return BaseResponse.newSuccResponse().result(queryByIdResponse).build();
    }

    @Override
    public BaseResponse queryByIdList(QueryByIdListRequest request) {
        QueryByIdListResponse queryByIdListResponse = new QueryByIdListResponse();
        List<Long> idList = request.getIdList();
        UserExample example = new UserExample();
        example.createCriteria().andIdIn(idList);
        List<User> users = userRepository.selectByExample(example);
        List<String> usernameList = new ArrayList<>();
        for (User user: users) {
            usernameList.add(user.getUsername());
        }
        queryByIdListResponse.setUsernameList(usernameList);
        return BaseResponse.newSuccResponse().result(queryByIdListResponse).build();
    }

}

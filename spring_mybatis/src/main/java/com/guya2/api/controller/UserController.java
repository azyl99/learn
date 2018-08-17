package com.guya2.api.controller;

import com.guya2.common.req.QueryByIdListRequest;
import com.guya2.common.req.QueryByIdRequest;
import com.guya2.common.response.BaseResponse;
import com.guya2.common.req.LoginRequest;
import com.guya2.contract.UserService;
import com.guya2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserService userService;

//    @ResponseBody
//    public String demo(@RequestParam("id") Long id) {
//        return userRepository.selectByPrimaryKey(id).getUsername();
//    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public BaseResponse queryReviewFakeList(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @RequestMapping(value = "/query/id")
    @ResponseBody
    public BaseResponse queryById(@Valid @RequestBody QueryByIdRequest request) {
        return userService.queryById(request);
    }

    @RequestMapping(value = "/query/idList")
    @ResponseBody
    public BaseResponse queryByIdList(@Valid @RequestBody QueryByIdListRequest request) {
        return userService.queryByIdList(request);
    }

}

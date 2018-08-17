package com.guya2.contract;

import com.guya2.common.req.LoginRequest;
import com.guya2.common.req.QueryByExampleRequest;
import com.guya2.common.req.QueryByIdListRequest;
import com.guya2.common.req.QueryByIdRequest;
import com.guya2.common.response.BaseResponse;

public interface UserService {

    BaseResponse login(LoginRequest request);

    BaseResponse queryById(QueryByIdRequest request);

    BaseResponse queryByIdList(QueryByIdListRequest request);

}

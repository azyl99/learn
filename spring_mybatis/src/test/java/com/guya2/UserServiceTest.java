package com.guya2;


import com.guya2.common.req.QueryByIdRequest;
import com.guya2.common.response.BaseResponse;
import com.guya2.common.response.QueryByIdResponse;
import com.guya2.contract.UserService;
import com.guya2.generate.common.domain.User;
import com.guya2.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContext.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    UserService userService;

    @Test
    public void queryByIdSuccTest() {

        BaseResponse<QueryByIdResponse> succResponse = BaseResponse.newSuccResponse().result(new QueryByIdResponse()).build();
        Mockito.when(userService.queryById(Mockito.argThat(o->o.getId() == 1L))).thenReturn(succResponse);
        QueryByIdRequest queryByIdRequest = new QueryByIdRequest();
        queryByIdRequest.setId(1L);
        BaseResponse<QueryByIdResponse> baseResponse = userService.queryById(queryByIdRequest);
        Assert.assertEquals(succResponse, baseResponse);
        Assert.assertEquals(succResponse.getResult(), baseResponse.getResult());
        Assert.assertEquals(succResponse.getResult().getUsername(), baseResponse.getResult().getUsername());
    }

    @Test
    public void queryByIdFailTest() {
        BaseResponse<QueryByIdResponse> failResponse = BaseResponse.newFailResponse().build();
        Mockito.when(userService.queryById(Mockito.argThat(o->o.getId() == 2L))).thenReturn(failResponse);
        QueryByIdRequest queryByIdRequest = new QueryByIdRequest();
        queryByIdRequest.setId(2L);
        BaseResponse<QueryByIdResponse> baseResponse = userService.queryById(queryByIdRequest);
        Assert.assertEquals(failResponse, baseResponse);
        Assert.assertEquals(failResponse.getResult(), baseResponse.getResult());
        Assert.assertEquals(failResponse.getResult().getUsername(), baseResponse.getResult().getUsername());
    }
}

package com.guya2.common.req;


import lombok.Data;
import javax.validation.constraints.NotNull;


@Data
public class LoginRequest extends Request {

    @NotNull
    String username;

    @NotNull
    String password;
}

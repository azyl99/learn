package com.guya2.common.response;

import lombok.Data;

import java.io.Serializable;


@Data
public class LoginResponse implements Serializable {

    String username;

    String password;

}

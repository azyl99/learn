package com.guya2.common.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class QueryByIdRequest extends Request {

    @NotNull
    Long id;

}

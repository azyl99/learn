package com.guya2.common.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QueryByExampleRequest extends Request {

    @NotNull
    List<Long> idList;

    Integer minAge;

}

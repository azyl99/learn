package com.guya2.common.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class QueryByIdListRequest extends Request {

    @NotNull
    List<Long> idList;

}

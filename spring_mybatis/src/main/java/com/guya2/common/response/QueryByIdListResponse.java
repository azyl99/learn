package com.guya2.common.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QueryByIdListResponse implements Serializable {

    List<String> usernameList;

}

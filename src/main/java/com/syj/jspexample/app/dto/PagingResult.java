package com.syj.jspexample.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagingResult<T> {

    private T data;

    private PagingStatus pagingStatus;

    private Status status;

}

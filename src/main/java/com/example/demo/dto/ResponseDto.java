package com.example.demo.dto;

import com.example.demo.common.CommonResponse;
import lombok.Getter;

@Getter
public class ResponseDto<T> {
    private final CommonResponse commonResponse;
    private final T data;

    public ResponseDto(CommonResponse commonResponse, T data) {
        this.commonResponse = commonResponse;
        this.data = data;
    }
}

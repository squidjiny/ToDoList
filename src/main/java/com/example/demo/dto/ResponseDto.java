package com.example.demo.dto;

import com.example.demo.common.CommonResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class ResponseDto<T> {
    @Schema(description = "성공 확인 메세지", example = "SUCCESS")
    private final CommonResponse commonResponse;
    @Schema(description = "템플릿 타입의 데이터.")
    private final T data;

    public ResponseDto(CommonResponse commonResponse, T data) {
        this.commonResponse = commonResponse;
        this.data = data;
    }
}

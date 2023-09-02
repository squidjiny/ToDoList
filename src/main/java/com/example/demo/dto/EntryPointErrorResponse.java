package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class EntryPointErrorResponse {

    private String msg;

    public EntryPointErrorResponse(String msg) {
        this.msg = msg;
    }
}

package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntryPointErrorResponse {

    private String msg;

    public EntryPointErrorResponse(String msg) {
        this.msg = msg;
    }
}

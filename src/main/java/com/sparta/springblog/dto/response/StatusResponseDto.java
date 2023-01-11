package com.sparta.springblog.dto.response;

import com.sparta.springblog.enums.StatusEnum;
import lombok.Getter;

@Getter
public class StatusResponseDto {
    private StatusEnum status;
    private String message;

    public StatusResponseDto(StatusEnum statusEnum, String message) {
        this.status = statusEnum;
        this.message = message;
    }
}

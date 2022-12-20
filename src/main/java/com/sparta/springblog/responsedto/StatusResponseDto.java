package com.sparta.springblog.responsedto;

import com.sparta.springblog.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusResponseDto {
    private StatusEnum status;
    private String message;

    public StatusResponseDto() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
    }
}

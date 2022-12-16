package com.sparta.springblog.responsedto;

import com.sparta.springblog.enums.StatusEnum;
import lombok.Data;

@Data
public class StatusResponseDto {
    private StatusEnum status;
    private String message;
    private Object data;

    public StatusResponseDto() {
        this.status = StatusEnum.BAD_REQUEST;
        this.message = null;
        this.data = null;
    }
}

package com.ahsan.file.app.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    public static int SUCCESS = 200;
    public static int NOT_SUCCESS = 400;
    private int code;

}

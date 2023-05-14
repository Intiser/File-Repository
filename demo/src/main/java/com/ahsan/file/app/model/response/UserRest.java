package com.ahsan.file.app.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRest {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isSuperAdmin;
}

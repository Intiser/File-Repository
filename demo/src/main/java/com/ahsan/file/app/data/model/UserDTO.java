package com.ahsan.file.app.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Entity;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    public static final long serialVersionUID = 4869732145698741236L;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isSuperAdmin;
}

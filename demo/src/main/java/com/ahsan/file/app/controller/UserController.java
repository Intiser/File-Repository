package com.ahsan.file.app.controller;

import com.ahsan.file.app.data.entity.UserEntity;
import com.ahsan.file.app.data.model.UserDTO;
import com.ahsan.file.app.model.response.UserRest;
import com.ahsan.file.app.model.resquest.UserDetailsRequestModel;
import com.ahsan.file.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;



    @GetMapping
    public Object getUser(){
        UserDTO user = new UserDTO();
        user.setFirstName("User");
        return user;
    }


    @PostMapping
    public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequestModel userdetails){
        System.out.println(userdetails.toString());
        UserRest returnValue = new UserRest();

        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userdetails, userDto);

        UserDTO created = userService.createUser(userDto);
        BeanUtils.copyProperties(created, returnValue);

        return ResponseEntity.ok(returnValue);
    }

    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String delleteUser(){
        return "delete user was called";
    }

}

package com.myhome.server.controller;

import com.myhome.server.dto.UserRequestDto;
import com.myhome.server.dto.UserResponseDto;
import com.myhome.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDto>> findUsers(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUsers(pageable));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/user/add")
    public ResponseEntity<UserResponseDto> addUser(@RequestBody UserRequestDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

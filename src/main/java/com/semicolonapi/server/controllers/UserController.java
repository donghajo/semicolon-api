package com.semicolonapi.server.controllers;

import com.semicolonapi.framework.models.ResponseDto;
import com.semicolonapi.framework.utils.ResponseUtil;
import com.semicolonapi.server.models.UserDto;
import com.semicolonapi.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseDto.Data getUsers() {
        List<UserDto.Info> result = userService.findAll();
        return ResponseUtil.returnList(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseDto.Data getUserByUserId(@PathVariable("userId") String userId) {
        UserDto.Info result = userService.findByUserId(userId);
        return ResponseUtil.returnSingle(result);
    }

}

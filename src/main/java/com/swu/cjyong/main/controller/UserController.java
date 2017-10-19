package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public ResponseEntity<User> getUserByAccountAndPasswd(@RequestParam String account, @RequestParam String passwd) {
        User user = userService.selectUserByAccountAndPasswd(account, passwd);
        if(null == user) {
            user=User.empty();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}

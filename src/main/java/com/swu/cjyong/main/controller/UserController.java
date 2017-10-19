package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public ResponseEntity<User> getUserByAccountAndPasswd(@RequestParam String account, @RequestParam String passwd) {
        User user = userService.selectUserByAccountAndPasswd(account, passwd);
        return new ResponseEntity<>(null == user ? User.empty() : user, HttpStatus.OK);
    }

    @ApiOperation(value = "用户更新")
    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        if (null == user || null == user.getId()) {
            return new ResponseEntity<>(User.empty(), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "获取下属用户")
    @GetMapping("/getUserByParentId")
    public ResponseEntity<List<User>> getUserByParentId(@RequestParam Long parentId) {
        return new ResponseEntity<>(userService.getUserByParentId(parentId), HttpStatus.OK);
    }

}

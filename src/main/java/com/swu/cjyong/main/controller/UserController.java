package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.dto.ComUser;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.SuperUserService;
import com.swu.cjyong.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SuperUserService superUserService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public ResponseEntity<ComUser> getUserByNameAndPasswd(@RequestParam String name, @RequestParam String passwd) {
        ComUser comUser = new ComUser();
        User user = userService.selectUserByNameAndPasswd(name, passwd);
        if(null == user){
            SuperUser superUser = superUserService.selectSuperUserByNameAndPasswd(name, passwd);
            comUser.setType(null == superUser ? 0 : 1);
            comUser.setSuperUser(superUser);
        } else {
            comUser.setType(2);
            comUser.setUser(user);
        }

        return new ResponseEntity<>(comUser, HttpStatus.OK);
    }

    @ApiOperation(value = "用户删除")
    @DeleteMapping("/deleteUser")
    public int deleteUser(@RequestParam Long selfId, @RequestParam Long userId){
        return (userService.deleteUser(selfId,userId));
    }

}

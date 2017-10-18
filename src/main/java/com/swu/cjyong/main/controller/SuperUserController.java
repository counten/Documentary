package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.ComUser;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.SuperUserService;
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
public class SuperUserController {

    @Autowired
    private SuperUserService superUserService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户登录")
    @GetMapping("/login")
    public ResponseEntity<ComUser> getUserByNameAndPasswd(@RequestParam String name, @RequestParam String passwd){

        ComUser comUser = new ComUser();
        User user = userService.selectUserByNameAndPasswd(name, passwd);
        if(null==user){
            SuperUser superUser = superUserService.selectSuperUserByNameAndPasswd(name, passwd);
            comUser.setType(1);
            System.out.println("m1");
            comUser.setSuperUser(superUser);
        }else {
            comUser.setType(3);
            System.out.println("m2");
            comUser.setUser(user);
        }

        return new ResponseEntity<ComUser>(comUser, HttpStatus.OK);
    }
}

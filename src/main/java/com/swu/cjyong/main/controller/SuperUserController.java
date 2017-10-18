package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.service.SuperUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/superusers")
public class SuperUserController {

    @Autowired
    private SuperUserService superUserService;

    @ApiOperation(value = "根据用户名密码获取用户信息")
    @GetMapping("/byNameAndPasswd")
    public ResponseEntity<SuperUser> getUserByNameAndPasswd(@RequestParam String name, @RequestParam String passwd){

        SuperUser superUser = superUserService.selectSuperUserByNameAndPasswd(name, passwd);

        return new ResponseEntity<SuperUser>(superUser == null ? SuperUser.empty() : superUser, HttpStatus.OK);
    }
}

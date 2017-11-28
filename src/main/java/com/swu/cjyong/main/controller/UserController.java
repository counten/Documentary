package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.BriefUser;
import com.swu.cjyong.main.entity.dto.MemberCount;
import com.swu.cjyong.main.service.ExcelService;
import com.swu.cjyong.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ExcelService excelService;

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

    @ApiOperation(value = "用户创建")
    @PutMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestParam Long selfId, @RequestBody User user){
        // 校验是否已经存在
        if(null != userService.findFirstByAccount(user.getAccount())) {
            return new ResponseEntity<>(User.empty(-2), HttpStatus.OK);
        }

        // 校验是否符合要求
        if (null == selfId || user.getUserType() < 3 || user.getUserType() > 4
                || user.getAccount() == null || user.getPasswd() == null) {
            return new ResponseEntity<>(User.empty(), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.createUser(selfId, user), HttpStatus.OK);
    }

    @ApiOperation(value = "获取下属用户")
    @GetMapping("/getUserByParentId")
    public ResponseEntity<List<User>> getUserByParentId(@RequestParam Long parentId) {
        return new ResponseEntity<>(userService.getUserByParentId(parentId), HttpStatus.OK);
    }

    @ApiOperation(value = "用户删除")
    @DeleteMapping("/deleteUser")
    public int deleteUser(@RequestParam Long selfId, @RequestParam Long userId){
        return (userService.deleteUser(selfId,userId));
    }

    @ApiOperation(value = "获取下属用户活动数量")
    @GetMapping("/getBelongsNumPass")
    public ResponseEntity<List<BriefUser>> getBelongsNumPass(@RequestParam Long selfId) {
        return new ResponseEntity<>(userService.getNumPassBelongs(selfId), HttpStatus.OK);
    }

    @ApiOperation(value = "获取所有的四级账户数量和团员数量")
    @GetMapping("getAllMeberNumAndAccout4")
    public ResponseEntity<MemberCount> getAllMeberNumAndAccout4(){
        return new ResponseEntity<>(userService.getAllMeberNumAndAccout4(), HttpStatus.OK);
    }

    @ApiOperation(value = "检查并重设所有的数据数据信息")
    @GetMapping("resetAllNumsInfo")
    public void checkAndUpdateMemberInfo(){
       userService.checkAndUpdateMemberInfo();
    }

    @ApiOperation(value = "获取统计信息excel")
    @GetMapping("getAllDataExcel")
    @ResponseBody
    public void getAllDataExcel(HttpServletResponse response){
        excelService.getAllDataExcel(response);
    }
}

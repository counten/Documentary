package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.ComActs;
import com.swu.cjyong.main.service.ActService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/act")
public class ActController {

    @Autowired
    private ActService actService;

    @ApiOperation(value = "活动信息")
    @GetMapping("/indexAct")
    public ResponseEntity<ComActs> indexAct(){
        return new ResponseEntity<>(actService.getIndexAct(), HttpStatus.OK);
    }
}

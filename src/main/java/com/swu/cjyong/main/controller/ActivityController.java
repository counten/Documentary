package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.ComUser;
import com.swu.cjyong.main.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/activitys")
public class ActivityController {

    @Value("${fileupload.storelocation}")
    private String fileLocation;

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "上传活动信息")
    @PostMapping("/uploadActivity")
    public ResponseEntity<Activity> uploadActivity(@RequestParam(value = "activity") Activity activity,
                                                   @RequestParam(value = "files") MultipartFile[] files) {
        //上传图片生成URL
        StringBuilder imgUrl = new StringBuilder();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String filePath = fileLocation;
            //处理中文乱码问题
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(filePath + fileName);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                imgUrl.append(filePath + fileName + ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        activity.setImg(imgUrl.toString());
        //插入到表中
        Activity result = activityService.uploadActivity(activity);
        return new ResponseEntity<Activity>(result == null ? Activity.empty() : activity, HttpStatus.OK);
    }
}

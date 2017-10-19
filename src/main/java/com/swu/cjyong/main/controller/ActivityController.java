package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Value("${fileupload.storelocation}")
    private String fileLocation;

    @Autowired
    private ActivityService activityService;

    @ApiOperation(value = "上传活动信息")
    @PostMapping("/uploadActivity")
    public ResponseEntity<Activity> uploadActivity(@RequestParam(value = "user_id") long user_id,
                                                   @RequestParam(value = "user_name") String user_name,
                                                   @RequestParam(value = "user_type") Integer user_type,
                                                   @RequestParam(value = "user_kind") Integer user_kind,
                                                   @RequestParam(value = "title") String title,
                                                   @RequestParam(value = "time") String time,
                                                   @RequestParam(value = "location") String location,
                                                   @RequestParam(value = "participants") String participants,
                                                   @RequestParam(value = "content") String content,
                                                   @RequestParam(value = "files") MultipartFile[] files) {
        Activity activity = new Activity()
                .setImg(imgFileupload(files))
                .setUserId(user_id)
                .setUserName(user_name)
                .setUserType(user_type)
                .setUserKind(user_kind)
                .setTime(time)
                .setTitle(title)
                .setLocation(location)
                .setParticipants(participants)
                .setContent(content)
                .setState(user_type == User.FORTH_USER ? Activity.ACT_CHECKING : Activity.ACT_PASS);
        Activity result = activityService.uploadActivity(activity);
        return new ResponseEntity<Activity>(result == null ? Activity.empty() : result, HttpStatus.OK);
    }
    

    /**
     * 上传图片获取链接
     *
     * @param files
     * @return
     */
    private String imgFileupload(MultipartFile[] files) {
        StringBuilder imgUrl = new StringBuilder();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //处理中文乱码问题
            fileName = UUID.randomUUID() + suffixName;
            File dest = new File(fileLocation + fileName);
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                imgUrl.append(fileLocation + fileName + ";");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return imgUrl.toString();
    }
}

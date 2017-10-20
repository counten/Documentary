package com.swu.cjyong.main.controller;

import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.ActivityIndex;
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
import java.util.List;
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
    public ResponseEntity<Activity> uploadActivity(@RequestParam(value = "userId") Long userId,
                                                   @RequestParam(value = "userName") String userName,
                                                   @RequestParam(value = "userType") Integer userType,
                                                   @RequestParam(value = "userKind") Integer userKind,
                                                   @RequestParam(value = "title") String title,
                                                   @RequestParam(value = "time") String time,
                                                   @RequestParam(value = "location") String location,
                                                   @RequestParam(value = "participants") String participants,
                                                   @RequestParam(value = "content") String content,
                                                   @RequestParam(value = "files") MultipartFile[] files) {
        Activity activity = new Activity()
                .setImg(imgFileupload(files))
                .setUserId(userId)
                .setUserName(userName)
                .setUserType(userType)
                .setUserKind(userKind)
                .setTime(time)
                .setTitle(title)
                .setLocation(location)
                .setParticipants(participants)
                .setContent(content)
                .setState(userType.equals(User.FORTH_USER) ? Activity.ACT_CHECKING : Activity.ACT_PASS);
        Activity result = activityService.uploadActivity(activity);
        return new ResponseEntity<>(result == null ? Activity.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "审批活动信息")
    @PutMapping("checkPass/byId/{selfId}/{actId}/{checkResult}")
    public ResponseEntity<Activity> checkPassingActivtyById(@PathVariable Long selfId,
                                                            @PathVariable Long actId,
                                                            @PathVariable Integer checkResultId) {
        Activity result = activityService.checkPassById(selfId, actId, checkResultId);
        return new ResponseEntity<>(result == null ? Activity.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "根据当前用户上传的活动")
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<Activity>> getActByUserId(@PathVariable Long userId){
        return new ResponseEntity<>(activityService.getActivityByUserId(userId), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID删除活动信息")
    @DeleteMapping("byId/{selfId}/{actId}")
    public ResponseEntity<Activity> deleteActivtyById(@PathVariable Long selfId, @PathVariable Long actId) {
        Activity result = activityService.deleteActivityById(selfId, actId);
        return new ResponseEntity<Activity>(result == null ? Activity.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "根据活动ID获取活动信息")
    @GetMapping("byActId/{actId}")
    public ResponseEntity<Activity> getActivtyById(@PathVariable Long actId) {
        Activity result = activityService.getActivityById(actId);
        return new ResponseEntity<Activity>(result == null ? Activity.empty() : result, HttpStatus.OK);
    }

    @ApiOperation(value = "根据活动类型/区域获取活动信息")
    @GetMapping("byKindId/{kind}")
    public ResponseEntity<List<Activity>> getActByKindId(@PathVariable Integer kind){
        return new ResponseEntity<>(activityService.getActivityByKindId(kind), HttpStatus.OK);
    }

    @ApiOperation(value = "根据活动状态获取活动信息")
    @GetMapping("byActState/{state}")
    public ResponseEntity<List<Activity>> getActByStateId(@PathVariable Integer state){
        return new ResponseEntity<>(activityService.getActivityByState(state), HttpStatus.OK);
    }

    @ApiOperation(value = "获取首页的活动信息")
    @GetMapping("actIndex")
    public ResponseEntity<ActivityIndex> getActIndex(){
        return new ResponseEntity<>(activityService.getIndexActivity(), HttpStatus.OK);
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
            if (file != null) {
                String fileName = file.getOriginalFilename();
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                //处理中文乱码问题
                fileName = UUID.randomUUID() + suffixName;
                File dest = new File(fileLocation + fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    file.transferTo(dest);
                    imgUrl.append(fileLocation + fileName + ";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgUrl.toString();
    }
}

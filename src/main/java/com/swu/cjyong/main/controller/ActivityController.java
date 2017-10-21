package com.swu.cjyong.main.controller;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
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
    @PutMapping("checkPass/byId/{selfId}/{actId}/{checkResultId}")
    public ResponseEntity<Activity> checkPassingActivtyById(@PathVariable Long selfId,
                                                            @PathVariable Long actId,
                                                            @PathVariable Long checkResultId) {
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

    @ApiOperation(value = "获取下属待审核活动")
    @GetMapping("bySelfId/{selfId}")
    public ResponseEntity<List<Activity>> getCheckingAct(@PathVariable Long selfId){
        return new ResponseEntity<>(activityService.getCheckingActivity(selfId), HttpStatus.OK);
    }

/*    @ApiOperation(value = "获取自己和下属对应状态的活动信息")
    @GetMapping("actIndex")
    public ResponseEntity<ActivityIndex> getActIndex(){
        return new ResponseEntity<>(activityService.getIndexActivity(), HttpStatus.OK);
    }*/





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
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgUrl.append(fileLocation + fileName + ";");
                uploadFileToQiniuYun(fileLocation + fileName);
            }
        }
        return imgUrl.toString();
    }

    private boolean uploadFileToQiniuYun(String fileUrl) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "your access key";
        String secretKey = "your secret key";
        String bucket = "your bucket name";
        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
        String localFilePath = "/home/qiniu/test.png";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(localFilePath, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return true;
    }


}

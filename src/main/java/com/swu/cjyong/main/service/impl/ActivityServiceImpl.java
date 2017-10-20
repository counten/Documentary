package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.ActivityIndex;
import com.swu.cjyong.main.entity.dto.BriefActivity;
import com.swu.cjyong.main.entity.dto.BriefUser;
import com.swu.cjyong.main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private UserRepository userRepository;

    public Activity uploadActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity checkPassById(Long selfId, Long actId, Integer checkResult) {
        Activity currentActivity = activityRepository.findOne(actId);

        if(currentActivity == null) {
            return null;
        }

        //权限检查
        User currentUser = userRepository.findOne(currentActivity.getUserId());
        if (currentUser.getParentId().equals(selfId) || currentUser.getPparentId().equals(selfId)) {
            currentActivity.setState(checkResult.equals(1) ? Activity.ACT_PASS : Activity.ACT_NOTPASS);
            return activityRepository.saveAndFlush(currentActivity);
        }

        return null;
    }

    public List<Activity> getActivityByUserId(Long userId) {
        return activityRepository.findByUserIdAndStateNot(userId, Activity.ACT_DELETE);
    }

    public Activity deleteActivityById(Long selfId, Long actId) {
        Activity currentActivity = activityRepository.findOne(actId);

        if (currentActivity == null) {
            return null;
        }

        //权限校验
        if (currentActivity.getUserId().equals(selfId)) {
            currentActivity.setState(Activity.ACT_DELETE);
            activityRepository.saveAndFlush(currentActivity);
            return currentActivity;
        }

        return null;
    }

    public Activity getActivityById(Long actId) {
        return activityRepository.findOne(actId);
    }

    public List<Activity> getActivityByKindId(Integer kind) {
        return activityRepository.findByUserKindAndState(kind, Activity.ACT_PASS);
    }

    public List<Activity> getActivityByState(Integer state) {
        return activityRepository.findByState(state);
    }

    public ActivityIndex getIndexActivity() {
        return new ActivityIndex()
                .setDistrict(getTopThreeBriefActivitysByKind(User.DISTRICT))
                .setCity(getTopThreeBriefActivitysByKind(User.CITY))
                .setSchool(getTopThreeBriefActivitysByKind(User.SCHOOL));
    }


    private List<BriefActivity> getTopThreeBriefActivitysByKind(Integer kind) {
        List<Activity> result = new ArrayList<Activity>();
        //先获取一条二级用户的活动信息
        Activity firstOne = activityRepository.findFirstByUserKindAndUserTypeAndStateOrderByCreateTime(
                kind, User.SECOND_USER, Activity.ACT_PASS);
        int size = 3;
        if (firstOne != null) {
            size = 2;
            result.add(firstOne);
        }

        PageRequest pr = new PageRequest(0, size);
        result.addAll(activityRepository.findByUserKindAndStateAndUserTypeNot(
                pr, kind, Activity.ACT_PASS, User.SECOND_USER
                ));
        return result.stream()
                .map(BriefActivity::Act2BriefAct)
                .collect(toList());
    }
}

package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public Activity checkPassById(Long selfId, Long actId) {
        Activity currentActivity = activityRepository.findOne(actId);

        if(currentActivity == null) {
            return null;
        }

        //权限检查
        User currentUser = userRepository.findOne(currentActivity.getUserId());
        if (currentUser.getParentId().equals(selfId) || currentUser.getPparentId().equals(selfId)) {
            currentActivity.setState(Activity.ACT_PASS);
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
        return activityRepository.findByUserKindAndStateNot(kind, Activity.ACT_DELETE);
    }

    public List<Activity> getActivityByState(Integer state) {
        return activityRepository.findByState(state);
    }

}

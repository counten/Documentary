package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    public Activity uploadActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Activity getActivityById(long id){
        return activityRepository.findOne(id);
    }

    public Activity deleteActivityById(long id) {
        Activity activity = activityRepository.findOne(id);
        if (activity != null) {
            activityRepository.delete(id);
        }
        return activity;
    }

    public Activity checkPassById(long id){
        Activity activity = activityRepository.findOne(id);
        if (activity != null) {
            activity.setState(Activity.ACTIVITY_PASSING);
            activityRepository.saveAndFlush(activity);
        }
        return activity;
    }
}

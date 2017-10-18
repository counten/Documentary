package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.dto.ComAct;
import com.swu.cjyong.main.entity.dto.ComActs;
import com.swu.cjyong.main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    @Override
    public ComActs getIndexAct() {
        ComActs comActs = new ComActs();
        comActs.setSchool(packageAct(Activity.USER_TYPE_SCHOOL));
        comActs.setDistrict(packageAct(Activity.USER_TYPE_DISTRICT));
        comActs.setEnterprise(packageAct(Activity.USER_TYPE_ENTERPRISE));
        return comActs;
    }

    public long countBySecondAccountId(long id) {
       return 1L;
    }


    private List<ComAct> packageAct(String userType){
        List<Activity> activities =new ArrayList<>();
        activities.add(activityRepository.findFirstByuserTypeAndUserGrade(userType, SuperUser.SECOND_USER));
        activities.add(activityRepository.findFirstByuserTypeAndUserGrade(userType,SuperUser.THIRD_USR));
        activities.add(activityRepository.findSecondByuserTypeAndUserGrade(userType,SuperUser.THIRD_USR));
        List<ComAct> school = new ArrayList<>();
        for(Activity t:activities){
            if(null != t){
                ComAct comAct = new ComAct();
                comAct.setId(t.getId());
                comAct.setImg(t.getImg());
                comAct.setTitle(t.getTitle());
                school.add(comAct);
            }
        }
        return school;
    }
}

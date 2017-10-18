package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.ComAct;
import com.swu.cjyong.main.entity.ComActs;
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
        comActs.setSchool(packageAct("school"));
        comActs.setDistrict(packageAct("district"));
        comActs.setEnterprise(packageAct("enterprise"));
        return comActs;
    }


    private List<ComAct> packageAct(String userType){
        List<Activity> activities =new ArrayList<>();
        activities.add(activityRepository.findFirstByuserTypeAndUserGradeOrderById(userType,"2"));
        activities.add(activityRepository.findFirstByuserTypeAndUserGradeOrderById(userType,"3"));
        activities.add(activityRepository.findSecondByuserTypeAndUserGradeOrderById(userType,"3"));
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

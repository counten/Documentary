package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.dao.SuperUserRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
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

    @Autowired
    private SuperUserRepository superUserRepository;

    @Autowired
    private UserRepository userRepository;

    public Activity uploadActivity(Activity activity) {
        if (activity.getUserGrade().equals(SuperUser.SECOND_USER)) { //二级活动添加
            SuperUser superUser = superUserRepository.findOne(activity.getUserId());
            superUser.setNums(superUser.getNums() + 1);
            superUserRepository.saveAndFlush(superUser);
        }
        return activityRepository.save(activity);
    }

    public Activity getActivityById(long id){
        return activityRepository.findOne(id);
    }

    public Activity deleteActivityById(long id) {       //二级用户活动数量减少
        Activity activity = activityRepository.findOne(id);
        if (activity != null) {
            //修改活动数量
            if (activity.getUserGrade().equals(SuperUser.SECOND_USER)) {
                SuperUser superUser = superUserRepository.findOne(activity.getUserId());
                superUser.setNums(Math.max(0, superUser.getNums() - 1));
            } else {
                User user = userRepository.findOne(activity.getUserId());
                SuperUser superUser = superUserRepository.findOne(user.getParentId());
                superUser.setNums(Math.max(0, superUser.getNums() - 1));
            }
            activityRepository.delete(id);
        }
        return activity;
    }

    public Activity checkPassById(long id){
        Activity activity = activityRepository.findOne(id);
        if (activity != null) {
            User user = userRepository.findOne(activity.getUserId());
            SuperUser superUser = superUserRepository.findOne(user.getParentId());
            superUser.setNums(superUser.getNums() + 1);
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
        long count = 0;
        SuperUser superUser = superUserRepository.findOne(id);
        if (superUser != null) {
            count = superUser.getNums();
        }
        return count;
    }

    @Override
    public List<ComAct> getActByType(String type) {
        List<Activity> activities = activityRepository.findByUserType(type);
        return ComAct.ActToComAct(activities);
    }


    private List<ComAct> packageAct(String userType){
        List<Activity> activities =new ArrayList<>();
        activities.add(activityRepository.findFirstByUserTypeAndUserGradeOrderById(userType, SuperUser.SECOND_USER));
        activities.add(activityRepository.findSecondByUserTypeAndUserGradeOrderById(userType,SuperUser.THIRD_USR));
        activities.add(activityRepository.findSecondByUserTypeAndUserGradeOrderById(userType,SuperUser.THIRD_USR));
        return ComAct.ActToComAct(activities);
    }
}

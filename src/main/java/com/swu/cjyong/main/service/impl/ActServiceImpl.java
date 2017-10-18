package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.ComAct;
import com.swu.cjyong.main.entity.ComActs;
import com.swu.cjyong.main.service.ActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActServiceImpl implements ActService {

    @Autowired
    private ActivityRepository activityRepository;


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
        activities.add(activityRepository.findFirstByuserTypeAndUserGrade(userType,"2"));
        activities.add(activityRepository.findFirstByuserTypeAndUserGrade(userType,"3"));
        activities.add(activityRepository.findSecondByuserTypeAndUserGrade(userType,"3"));
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

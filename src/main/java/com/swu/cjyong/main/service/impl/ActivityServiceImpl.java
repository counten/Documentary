package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.ActivityIndex;
import com.swu.cjyong.main.entity.dto.BriefActivity;
import com.swu.cjyong.main.entity.dto.BriefUser;
import com.swu.cjyong.main.service.ActivityService;
import org.jboss.jandex.PrimitiveType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        //活动数量更新
        if (activity.getState().equals(Activity.ACT_CHECKING)) {
            User actOwner = userRepository.findOne(activity.getUserId());
            actOwner.setNumCheck(actOwner.getNumCheck() + 1);
            userRepository.saveAndFlush(actOwner);
            if (actOwner.getParentId() != null) {
                User parent = userRepository.findOne(actOwner.getParentId());
                parent.setNumCheck(parent.getNumCheck() + 1);
                userRepository.saveAndFlush(parent);
            }
            if (actOwner.getPparentId() != null) {
                User pparent = userRepository.findOne(actOwner.getPparentId());
                pparent.setNumCheck(pparent.getNumCheck() + 1);
                userRepository.saveAndFlush(pparent);
            }
        } else {
            User actOwner = userRepository.findOne(activity.getUserId());
            actOwner.setNumPass(actOwner.getNumPass() + 1);
            userRepository.saveAndFlush(actOwner);
            if (actOwner.getParentId() != null) {
                User parent = userRepository.findOne(actOwner.getParentId());
                parent.setNumPass(parent.getNumPass() + 1);
                userRepository.saveAndFlush(parent);
            }
        }
        return activityRepository.save(activity);
    }

    public Activity checkPassById(Long selfId, Long actId, Long checkResult) {
        Activity currentActivity = activityRepository.findOne(actId);
        if(currentActivity == null) {
            return null;
        }
        //权限检查
        User currentUser = userRepository.findOne(currentActivity.getUserId());
        if (currentUser.getParentId().equals(selfId)) {             //允许审批
            User actOwner = userRepository.findOne(currentActivity.getUserId());
            actOwner.setNumCheck(Math.max(0, actOwner.getNumCheck() - 1));
            if (checkResult.equals(1L)) {
                actOwner.setNumPass(actOwner.getNumPass() + 1);
            } else {
                actOwner.setNumNotPass(actOwner.getNumNotPass() + 1);
            }
            userRepository.saveAndFlush(actOwner);
            if (actOwner.getParentId() != null) {
                User parent = userRepository.findOne(actOwner.getParentId());
                parent.setNumCheck(Math.max(0, parent.getNumCheck() - 1));
                if (checkResult.equals(1L)) {
                    parent.setNumPass(parent.getNumPass() + 1);
                } else {
                    parent.setNumNotPass(parent.getNumNotPass() + 1);
                }
                userRepository.saveAndFlush(parent);
            }
            if (actOwner.getPparentId() != null) {
                User pparent = userRepository.findOne(actOwner.getPparentId());
                pparent.setNumCheck(Math.max(0, pparent.getNumCheck() - 1));
                if (checkResult.equals(1L)) {
                    pparent.setNumPass(pparent.getNumPass() + 1);
                } else {
                    pparent.setNumNotPass(pparent.getNumNotPass() + 1);
                }
                userRepository.saveAndFlush(pparent);
            }

            currentActivity.setState(checkResult.equals(1L) ? Activity.ACT_PASS : Activity.ACT_NOTPASS);
            return activityRepository.saveAndFlush(currentActivity);
        }
        return null;
    }

    public List<Activity> getActivityByUserId(Long userId) {
        return activityRepository.findByUserIdAndStateNot(userId, Activity.ACT_DELETE);
    }

    public Activity deleteActivityById(Long selfId, Long actId) {
        Activity currentActivity = activityRepository.findOne(actId);

        if (currentActivity == null || currentActivity.getState().equals(Activity.ACT_DELETE)) {
            return null;
        }

        //权限校验
        if (currentActivity.getUserId().equals(selfId)) {   //允许删除
            User currentUser = userRepository.findOne(selfId);
            currentUser.setNumDelete(currentUser.getNumDelete() + 1);
            Integer currentState = currentActivity.getState();
            userRepository.saveAndFlush(stateKeep(currentState, currentUser));
            if (currentUser.getParentId() != null) {
                User parent = userRepository.findOne(currentUser.getParentId());
                parent.setNumDelete(parent.getNumDelete() + 1);
                userRepository.saveAndFlush(stateKeep(currentState, parent));
            }
            if (currentUser.getPparentId() != null) {
                User pparent = userRepository.findOne(currentUser.getPparentId());
                pparent.setNumDelete(pparent.getNumDelete() + 1);
                userRepository.saveAndFlush(pparent);
                userRepository.saveAndFlush(stateKeep(currentState, pparent));
            }
            currentActivity.setState(Activity.ACT_DELETE);
            activityRepository.saveAndFlush(currentActivity);
            return currentActivity;
        }

        return null;
    }

    // 状态维护子函数
    private static User stateKeep(Integer currentState, User user){
        if (currentState.equals(Activity.ACT_CHECKING)) {
            user.setNumCheck(Math.max(0, user.getNumCheck() - 1));
        } else if (currentState.equals(Activity.ACT_PASS)) {
            user.setNumPass(Math.max(0, user.getNumPass() - 1));
        } else {
            user.setNumNotPass(Math.max(0, user.getNumNotPass() - 1));
        }
        return user;
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

    @Override
    public List<Activity> getCheckingActivity(Long selfId) {
        User user = userRepository.findOne(selfId);
        List<Activity> activities = new ArrayList<>();
        if (null == user || user.getUserType() < User.SECOND_USER ||
                user.getUserType()>User.THIRD_USER ){
            return activities;
        }
        List<User> users = userRepository.findByUserTypeAndParentId(User.FORTH_USER, selfId);

        for (User u:users){
            List<Activity> activities1 = activityRepository.findByStateAndUserId(Activity.ACT_CHECKING, u.getId());
            for (Activity act:activities1){
                if (null != act){
                    activities.add(act);
                }
            }
        }
        return activities;
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

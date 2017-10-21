package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.dto.ActivityIndex;
import com.swu.cjyong.main.entity.dto.BriefUser;

import javax.validation.constraints.Digits;
import java.util.List;

public interface ActivityService {
    /**
     * 上传活动信息
     *
     * @param activity
     * @return
     */
    Activity uploadActivity(Activity activity);

    /**
     * 审批通过活动信息
     *
     * @param selfId
     * @param actId
     * @return
     */
    Activity checkPassById(Long selfId, Long actId, Long checkResult);

    /**
     * 获取当前用户活动信息
     *
     * @param userId
     * @return
     */
    List<Activity> getActivityByUserId(Long userId);

    /**
     * 根据ID删除活动信息, 默认只可以删除自己的活动信息
     *
     * @param selfId
     * @param actId
     * @return
     */
    Activity deleteActivityById(Long selfId, Long actId);

    /**
     * 根据活动ID获取活动信息
     *
     * @param actId
     * @return
     */
    Activity getActivityById(Long actId);

    /**
     * 根据活动类型获取活动列表
     *
     * @param kind
     * @return
     */
    List<Activity> getActivityByKindId(Integer kind);

    /**
     * 根据活动状态获取活动列表
     *
     * @param state
     * @return
     */
    List<Activity> getActivityByState(Integer state);

    /**
     * 获取首页活动列表
     *
     * @return
     */
    ActivityIndex getIndexActivity();

    /**
     * 获取用户的待审核活动信息
     * @Param selfId
     * @return List<Activity>
     */
    List<Activity> getCheckingActivity(Long selfId);
}

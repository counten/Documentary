package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.ActivityRepository;
import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity uploadActivity(Activity activity) {
        return activityRepository.save(activity);
    }

}

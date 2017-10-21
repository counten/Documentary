package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUserIdAndStateNot(Long userId, Integer userState);
    List<Activity> findByUserKindAndState(Integer userKind, Integer userState);
    List<Activity> findByStateAndUserId(Integer state, Long userId);
    List<Activity> findByState(Integer state);
    Activity findFirstByUserKindAndUserTypeAndStateOrderByCreateTime(
            Integer userKind,
            Integer userType,
            Integer userState);
    List<Activity> findByUserKindAndStateAndUserTypeNot(Pageable pageable, Integer userState, Integer userKind, Integer userType);
}

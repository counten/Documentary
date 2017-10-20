package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUserIdAndState(Long userId, Integer userState);
    List<Activity> findByUserKindAndState(Integer userKind, Integer userState);
    List<Activity> findByState(Integer state);
    Activity findFirstByUserKindAndUserTypeAndStateOrderByCreateTime(
            Integer userKind,
            Integer userType,
            Integer userState);
    List<Activity> findByUserKindAndStateAndUserTypeNotIn(Pageable pageable, Integer userState, Integer userKind, Integer userType);
}

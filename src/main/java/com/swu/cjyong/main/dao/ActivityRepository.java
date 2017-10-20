package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByUserIdAndStateNot(Long userId, Integer userState);
    List<Activity> findByUserKindAndStateNot(Integer kind, Integer userState);
    List<Activity> findByState(Integer state);
}

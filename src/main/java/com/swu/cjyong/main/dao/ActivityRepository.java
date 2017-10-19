package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import com.swu.cjyong.main.entity.dto.ComAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    Activity findFirstByUserTypeAndUserGradeOrderById(String userType, String userGrade);

    Activity findSecondByUserTypeAndUserGradeOrderById(String userType, String userGrade);

    List<Activity> findByUserId(Long id);

    List<Activity> findByUserType(String userType);
}


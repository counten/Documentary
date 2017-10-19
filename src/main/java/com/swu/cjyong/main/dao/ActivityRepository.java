package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    Activity findFirstByUserTypeAndUserGradeOrderById(String userType, String userGrade);

    Activity findFirstByUserTypeAndUserGradeAndIdNotOrderById(String userType, String userGrade, Long id);

/*    Activity findFirstByUserTypeAndUserGradeOrderById(String userType, String userGrade, Long id);*/

    List<Activity> findByUserId(Long id);

//    @Query("SELECT id, title, img FROM activity " +
//            "WHERE activity.userType=?1 AND activity.userGrade=?2")
//    List<ComAct> getActivity(@Param("userType") String userType,
//                             @Param("userGrade") String userGrade);
}


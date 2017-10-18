package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {

    Activity findFirstByuserTypeAndUserGrade(String userType, String userGrade);

    Activity findSecondByuserTypeAndUserGrade(String userType, String userGrade);

//    @Query("SELECT id, title, img FROM activity " +
//            "WHERE activity.userType=?1 AND activity.userGrade=?2")
//    List<ComAct> getActivity(@Param("userType") String userType,
//                             @Param("userGrade") String userGrade);
}


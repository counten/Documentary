package com.swu.cjyong.main.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="activity")
@Data
@Accessors(chain = true)
public class Activity {
    public static final String ACTIVITY_CHECKING = "checking";          //审核中
    public static final String ACTIVITY_PASSING = "passing";            //通过
    public static final String ACTIVITY_NOTPASSING = "not_passing";     //未通过

    public static final String USER_TYPE_SCHOOL = "school";             //学校
    public static final String USER_TYPE_DISTRICT = "district";         //区县
    public static final String USER_TYPE_ENTERPRISE = "enterprise";     //企业

    public static Activity empty(){
        return new Activity((long)-1);
    }

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private Long userId;
    @NonNull
    private String userName;
    @NonNull
    private String userType;
    @NonNull
    private String userGrade;
    @NonNull
    private String title;
    @NonNull
    private String time;
    @NonNull
    private String location;
    @NonNull
    private String member;
    @NonNull
    private String content;
    private String img;
    private String state;


    public Activity() {};

    public Activity(Long id){
        this.id = id;
    };
}

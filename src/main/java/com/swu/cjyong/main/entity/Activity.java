package com.swu.cjyong.main.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name="activity")
@Data
@Accessors(chain = true)
public class Activity {
    // 全局变量
    public static final int ACT_CHECKING = 1;
    public static final int ACT_PASS = 2;
    public static final int ACT_NOTPASS = 3;
    public static final int ACT_DELETE = 4;


    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private String userName;

    @NonNull
    private Integer userType;

    @NonNull
    private Integer userKind;

    @NonNull
    private String title;

    @NonNull
    private String time;

    private Long createTime = System.currentTimeMillis();

    @NonNull
    private String location;

    @NonNull
    private Long participantsNum;

    private String participants;

    @NonNull
    private String content;

    private String img;

    @NonNull
    private Integer state;

    public Activity() {}

    public Activity(Long id){
        this.id = id;
    }

    public static Activity empty(){
        return new Activity((long)-1);
    }

}

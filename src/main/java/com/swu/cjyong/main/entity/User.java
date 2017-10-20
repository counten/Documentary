package com.swu.cjyong.main.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
@Data
@Accessors(chain = true)
public class User {
    // 全局变量
    public static final int TOP_USER = 1;
    public static final int SECOND_USER = 2;
    public static final int THIRD_USER = 3;
    public static final int FORTH_USER = 4;

    public static final int DISTRICT = 1;
    public static final int CITY = 2;
    public static final int SCHOOL = 3;


    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String account;

    @NonNull
    private String passwd;

    @NonNull
    private Integer userType;

    @NonNull
    private String name;

    private Long parentId;

    private Long pparentId;

    private String secretaryName;

    private String secretaryTel;

    private Integer memberNum;

    @NonNull
    private Integer userKind;

    private Integer numCheck = 0;
    private Integer numPass = 0;
    private Integer numNotPass = 0;
    private Integer numDelete = 0;


    public User() {}

    public User(Long id){
        this.id = id;
    }

    public static User empty(){
        return new User((long)-1);
    }

    public static User empty(int state){
        return new User((long)state);
    }
}

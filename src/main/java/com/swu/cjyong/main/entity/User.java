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

    public static User empty(){
        return new User((long)-1);
    }

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String passwd;
    @NonNull
    private String descr;
    @NonNull
    private String admin;
    @NonNull
    private String admin2;
    @NonNull
    private String admin3;
    @NonNull
    private String tel;
    @NonNull
    private Long parentId;
    @NonNull
    private String type;

    public User() {};

    public User(Long id){
        this.id = id;
    };
}

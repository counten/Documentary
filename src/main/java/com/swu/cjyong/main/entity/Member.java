package com.swu.cjyong.main.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
@Data
@Accessors(chain = true)
public class Member {

    public static Member empty(){
        return new Member((long)-1);
    }

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private String sex;
    @NonNull
    private String tel;
    @NonNull
    private Long orgId;


    public Member() {};

    public Member(Long id){
        this.id = id;
    };
}

package com.swu.cjyong.main.entity;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="superuser")
@Data
@Accessors(chain = true)
public class SuperUser {
    public static final String FIRST_USRE = "TOP";
    public static final String SECOND_USER = "SECOND";
    public static final String THIRD_USR = "THIRD";



    public static SuperUser empty(){
        return new SuperUser((long)-1);
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
    private String grade;
    @NonNull
    private String type;
    private long nums;

    public SuperUser() {};

    public SuperUser(Long id){
        this.id = id;
    };
}

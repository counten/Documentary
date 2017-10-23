package com.swu.cjyong.main.entity.dto;

import lombok.Data;

@Data
public class MemberCount {
    private int memberCount = 0;
    private int account4Count = 0;
    private int member_school = 0;
    private int account4_school = 0;
    private int member_city = 0;
    private int account4_city = 0;
    private int member_district = 0;
    private int account4_district = 0;

    public void addAll() {
        memberCount = member_city + member_school + member_district;
        account4Count = account4_city + account4_district + account4_school;
    }
}

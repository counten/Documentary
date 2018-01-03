package com.swu.cjyong.main.entity.dto;

import lombok.Data;

@Data
public class MemberCount {
    private long memberCount = 0;
    private long account4Count = 0;
    private long member_school = 0;
    private long account4_school = 0;
    private long member_city = 0;
    private long account4_city = 0;
    private long member_district = 0;
    private long account4_district = 0;

    public void addAll() {
        memberCount = member_city + member_school + member_district;
        account4Count = account4_city + account4_district + account4_school;
    }
}

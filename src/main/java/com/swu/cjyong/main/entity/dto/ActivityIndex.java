package com.swu.cjyong.main.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ActivityIndex {
    List<BriefActivity> district;
    List<BriefActivity> city;
    List<BriefActivity> school;
}

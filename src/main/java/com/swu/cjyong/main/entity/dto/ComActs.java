package com.swu.cjyong.main.entity.dto;

import java.util.List;

public class ComActs {
    private List<ComAct> school;
    private List<ComAct> district;
    private List<ComAct> enterprise;

    public List<ComAct> getSchool() {
        return school;
    }

    public void setSchool(List<ComAct> school) {
        this.school = school;
    }

    public List<ComAct> getDistrict() {
        return district;
    }

    public void setDistrict(List<ComAct> district) {
        this.district = district;
    }

    public List<ComAct> getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(List<ComAct> enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "ComActs{" +
                "school=" + school +
                ", district=" + district +
                ", enterprise=" + enterprise +
                '}';
    }
}

package com.swu.cjyong.main.entity.dto;

public class ComTypes {
    private int school;
    private int district;
    private int enterprise;

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(int enterprise) {
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "ComTypes{" +
                "school=" + school +
                ", district=" + district +
                ", enterprise=" + enterprise +
                '}';
    }
}

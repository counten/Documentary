package com.swu.cjyong.main.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class ComAct {
    private long id;
    private String title;
    private String img;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static List<ComAct> ActToComAct(List<Activity> activities){
        List<ComAct> comActs = new ArrayList<>();
        for(Activity activity:activities) {
            if(null != activity){
                ComAct comAct = new ComAct();
                comAct.setId(activity.getId());
                comAct.setImg(activity.getImg());
                comAct.setTitle(activity.getTitle());
                comActs.add(comAct);
            }
        }
        return comActs;
    }

    @Override
    public String toString() {
        return "ComAct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

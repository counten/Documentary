package com.swu.cjyong.main.entity.dto;

import com.swu.cjyong.main.entity.Activity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BriefActivity {
    private long id;
    private String title;
    private String img;

    public static BriefActivity Act2BriefAct(Activity activity) {
        if (activity != null) {
            return new BriefActivity()
                    .setId(activity.getId())
                    .setImg(activity.getImg())
                    .setTitle(activity.getTitle());
        }
        return null;
    }
}

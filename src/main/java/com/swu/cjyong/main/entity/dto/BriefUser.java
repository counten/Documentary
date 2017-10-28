package com.swu.cjyong.main.entity.dto;

import com.swu.cjyong.main.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BriefUser {
    private Long id;
    private String name;
    private String account;
    private int numPass = 0;
    private long participantsNum;
    private int userKind;

    public static BriefUser UserToBriefUser(User user){
        if (user == null) return null;
        return new BriefUser()
                .setId(user.getId())
                .setName(user.getName())
                .setAccount(user.getAccount())
                .setUserKind(user.getUserKind())
                .setNumPass(null == user.getNumPass() ? 0 : user.getNumPass())
                .setParticipantsNum(null == user.getParticipantsNum() ? 0 : user.getParticipantsNum());
    }
}

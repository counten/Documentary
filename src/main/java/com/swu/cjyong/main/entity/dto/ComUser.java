package com.swu.cjyong.main.entity.dto;


import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;

public class ComUser {
    private int type;
    private User user;
    private SuperUser superUser;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SuperUser getSuperUser() {
        return superUser;
    }

    public void setSuperUser(SuperUser superUser) {
        superUser.setPasswd("");
        this.superUser = superUser;
    }

    @Override
    public String toString() {
        return "ComUser{" +
                "type=" + type +
                ", user=" + user +
                ", superUser=" + superUser +
                '}';
    }
}

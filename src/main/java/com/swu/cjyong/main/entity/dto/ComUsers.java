package com.swu.cjyong.main.entity.dto;

import java.util.List;

public class ComUsers {
    private int type;
    private List<User> users;
    private List<SuperUser> superUsers;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<SuperUser> getSuperUsers() {
        return superUsers;
    }

    public void setSuperUsers(List<SuperUser> superUsers) {
        this.superUsers = superUsers;
    }

    @Override
    public String toString() {
        return "ComUsers{" +
                "type=" + type +
                ", users=" + users +
                ", superUsers=" + superUsers +
                '}';
    }
}

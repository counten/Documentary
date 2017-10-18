package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;

public interface UserService {

    /**
     * 根据用户名和密码获取三级用户信息
     *
     * @param name
     * @param passwd
     * @return User
     */
    User selectUserByNameAndPasswd(String name, String passwd);
}

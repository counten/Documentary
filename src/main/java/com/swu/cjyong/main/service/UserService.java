package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;

public interface UserService {

    /**
     * 根据用户名和密码获取三级用户信息
     *
     * @param account
     * @param passwd
     * @return User
     */
    User selectUserByAccountAndPasswd(String account, String passwd);
}

package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.SuperUser;

import java.util.Optional;

public interface SuperUserService {

    /**
     * 根据用户名和密码获取管理员信息
     *
     * @param name
     * @param passwd
     * @return
     */
    SuperUser selectSuperUserByNameAndPasswd(String name, String passwd);
}
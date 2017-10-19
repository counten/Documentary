package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名和密码获取三级用户信息
     *
     * @param account
     * @param passwd
     * @return User
     */
    User selectUserByAccountAndPasswd(String account, String passwd);

    /**
     * 更新用户信息
     *
     * @param User
     * @return User
     */
    User updateUser(User user);

    /**
     * 根据用户类型获取下属用户列表
     * @param selfId
     * @return List<User>
     */
    List<User> getUserByParentId(Long selfId);

}

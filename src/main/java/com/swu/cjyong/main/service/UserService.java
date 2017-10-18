package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名和密码获取三级用户信息
     *
     * @param name
     * @param passwd
     * @return User
     */
    User selectUserByNameAndPasswd(String name, String passwd);

    /**
     * 根据自身id和用户id删除用户
     *
     * @param selfId
     * @param userId
     * @return 0删除成功
     */
    int deleteUser(Long selfId, Long userId);

    /**
     * 根据自身id查询所属id
     *
     * @param parentId
     * @return User集合
     */
    List<User> findUserByParentId(Long parentId);
}

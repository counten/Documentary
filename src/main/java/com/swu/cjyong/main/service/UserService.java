package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.ComUsers;


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
    ComUsers findUserByParentId(Long parentId);

    /**
     * 根据自身id修改用户信息
     *
     * @param user
     * @return user
     */
    User updateUser(User user);
}

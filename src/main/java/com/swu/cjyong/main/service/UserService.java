package com.swu.cjyong.main.service;

import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.BriefUser;

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

    /**
     * 创建不同用户
     * @param selfId
     * @param User
     * @return User
     */
    User createUser(Long selfId, User user);


    /**
     * 删除不同用户
     * @param selfId
     * @param userId
     */
     int deleteUser(Long selfId, Long userId);

    /**
     * 获取下级上传活动数量
     * @Param selfId
     * @return List<BriefUser>
     */

    List<BriefUser> getNumPassBelongs(Long selfId);
}

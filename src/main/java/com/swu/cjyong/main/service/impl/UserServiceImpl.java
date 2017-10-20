package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.BriefUser;
import com.swu.cjyong.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User selectUserByAccountAndPasswd(String account, String passwd) {
        return userRepository.findFirstByAccountAndPasswd(account,passwd);
    }

    @Override
    public User updateUser(User user) {
        User user_new = new User();
        try {
            user_new = userRepository.save(user);
        } catch (Exception e) {
            user_new = User.empty();
            System.out.println("Exception: updateUser UserServiceImpl");
        } finally {
            return user_new;
        }
    }

    @Override
    public List<User> getUserByParentId(Long selfId) {
        User user = userRepository.findOne(selfId);
        return  (user== null || user.getUserType() == User.FORTH_USER) ? new ArrayList<>():
                userRepository.findByParentId(selfId);
    }

    @Override
    public User createUser(Long selfId, User user) {
        User user_new = new User();
        try {
            User pUser = userRepository.findOne(selfId);
            // 默认属性处理
            user.setUserKind(pUser.getUserKind());
            if (user.getUserType() == User.THIRD_USER && pUser.getUserType() == User.SECOND_USER) {
                user.setName(null == user.getName() ? "***团委" : user.getName());
            } else {
                user.setUserType(User.FORTH_USER);
                user.setName(null == user.getName() ? "***团支部" : user.getName());
            }
            user.setId(null);
            user.setSecretaryName("***书记");
            user.setSecretaryTel(null);

            System.out.println("final User"+user);
            user_new = userRepository.saveAndFlush(user);
        } catch (Exception e){
            user_new = User.empty();
        } finally {
            return user_new;
        }
    }

    @Override
    public List<BriefUser> getNumPassBelongs(Long selfId) {
        List<BriefUser> briefUsers = new ArrayList<>();
        // selfId 不存在
        User pUser = userRepository.findOne(selfId);
        if (null == pUser) {
            return briefUsers;
        }

        // 获取下级用户
        List<User> users = new ArrayList<>();
        if(pUser.getUserType() == User.TOP_USER){
            users = userRepository.findByUserType(User.SECOND_USER);
        } else {
            users = userRepository.findByUserTypeAndParentId(pUser.getUserType()+1, selfId);
        }

        // 没有下级用户
        if (users.size() == 0){
            return briefUsers;
        }

        // 获取每个下级用户的数量
        for (User u: users){
            briefUsers.add(BriefUser.UserToBriefUser(u));
        }

        return briefUsers;
    }

    @Override
    public int deleteUser(Long selfId, Long userId) {
        User user = userRepository.findOne(userId);
        // 判断存在和所属关系
        if (null == user || user.getParentId() != selfId) {
            return 1;
        }
        // 级联删除
        if(user.getUserType() == User.THIRD_USER) {
            userRepository.deleteByParentId(userId);
        }
        userRepository.delete(userId);

        return 0;
    }
}

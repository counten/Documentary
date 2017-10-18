package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public User selectUserByNameAndPasswd(String name, String passwd){
        return UserRepository.findFirstByNameAndPasswd(name, passwd);
    }

    @Override
    public int deleteUser(Long selfId, Long userId) {
        if(selfId !=1){
            return 1;
        }
        try {
            UserRepository.delete(userId);
            return 0;
        }catch (Exception e){
            System.out.println("Error:deleteUser in UserServiceImpl");
        }
        return 1;
    }

    @Override
    public List<User> findUserByParentId(Long parentId) {
        return UserRepository.findUserByParentId(parentId);
    }
}

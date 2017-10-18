package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.SuperUserRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.ComUsers;
import com.swu.cjyong.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private SuperUserRepository superUserRepository;

    @Override
    public User selectUserByNameAndPasswd(String name, String passwd){
        return UserRepository.findFirstByNameAndPasswd(name, passwd);
    }

    @Override
    public int deleteUser(Long selfId, Long userId) {
        SuperUser superUser = superUserRepository.findOne(selfId);
        User user = UserRepository.findOne(userId);

        if(superUser == null || user == null){
            return 1;
        }

        if(superUser.getType().equals(SuperUser.FIRST_USRE)) {
            superUserRepository.delete(userId);
            UserRepository.deleteByParentId(userId);
            return 0;
        } else {
            if(superUser.getId().equals(user.getParentId())) {
                UserRepository.delete(userId);
                return 0;
            }
        }

        return 1;
    }

    @Override
    public ComUsers findUserByParentId(Long parentId) {
        SuperUser superUser = superUserRepository.findOne(parentId);
        if(null == superUser){
            return null;
        }

        ComUsers comUsers = new ComUsers();
        if(superUser.getType().equals(SuperUser.FIRST_USRE)) {
            comUsers.setType(1);
            comUsers.setSuperUsers(superUserRepository.findAll());
        } else if(superUser.getType().equals(SuperUser.SECOND_USER)){
            comUsers.setType(2);
            comUsers.setUsers(UserRepository.findUserByParentId(parentId));
        }
        return comUsers;
    }

    @Override
    public User updateUser(User user) {
        if(user.getId() == null || UserRepository.findOne(user.getId())== null){
            return null;
        }
        return UserRepository.save(user);
    }
}

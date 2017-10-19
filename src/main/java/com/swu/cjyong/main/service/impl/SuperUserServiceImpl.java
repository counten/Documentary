package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.SuperUserRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SuperUserServiceImpl implements SuperUserService{

    @Autowired
    private SuperUserRepository superUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SuperUser selectSuperUserByNameAndPasswd(String name, String passwd){
        return superUserRepository.findFirstByNameAndPasswd(name, passwd);
    }

    @Override
    public int deleteSuperUser(Long topId, Long superUserId) {
        SuperUser topUser = superUserRepository.findOne(topId);
        SuperUser superUser = superUserRepository.findOne(superUserId);

        if(superUser == null || topUser == null){
            return 1;
        }
        if (superUserRepository.findOne(topId).getType().equals(SuperUser.FIRST_USRE)) {
            superUserRepository.delete(superUserId);
            userRepository.deleteByParentId(superUserId);
        }
        return 0;
    }
}

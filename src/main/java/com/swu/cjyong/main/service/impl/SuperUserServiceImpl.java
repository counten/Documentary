package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.SuperUserRepository;
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

    @Override
    public SuperUser selectSuperUserByNameAndPasswd(String name, String passwd){
        return superUserRepository.findFirstByNameAndPasswd(name, passwd);
    }

    @Override
    public int deleteSuperUser(Long selfId, Long userId) {

        if(selfId !=1){
            return 1;
        }

        try {
            superUserRepository.delete(userId);
            return 0;
        }catch (Exception e){
            System.out.println("Error:deleteSuperUser in SuperUserServiceImpl");
        }
        return 1;
    }
}

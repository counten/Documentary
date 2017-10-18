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
}

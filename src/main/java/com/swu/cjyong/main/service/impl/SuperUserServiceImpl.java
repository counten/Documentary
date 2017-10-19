package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.SuperUserRepository;
import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.SuperUser;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.service.MemberService;
import com.swu.cjyong.main.service.SuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SuperUserServiceImpl implements SuperUserService{

    @Autowired
    private SuperUserRepository superUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemberService memberService;

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
            List<User> users = userRepository.findUserByParentId(superUserId);
            for(User u:users) {
                memberService.deleteByOrgId(u.getId());
            }
            userRepository.deleteByParentId(superUserId);
        }
        return 0;
    }
}

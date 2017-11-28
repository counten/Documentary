package com.swu.cjyong.main.service.impl;

import com.swu.cjyong.main.dao.UserRepository;
import com.swu.cjyong.main.entity.User;
import com.swu.cjyong.main.entity.dto.BriefUser;
import com.swu.cjyong.main.entity.dto.MemberCount;
import com.swu.cjyong.main.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User selectUserByAccountAndPasswd(String account, String passwd) {
        return userRepository.findFirstByAccountAndPasswd(account,passwd);
    }

    @Override
    public User updateUser(User user) {
        User user_new = null;
        //保留之前的数据信息
        User user_old = userRepository.findOne(user.getId());
        user.setNumNotPass(user_old.getNumNotPass())
                .setNumPass(user_old.getNumPass())
                .setNumCheck(user_old.getNumCheck())
                .setNumDelete(user_old.getNumDelete())
                .setParticipantsNum(user_old.getParticipantsNum());
        try {
            user_new = userRepository.save(user);
        } catch (Exception e) {
            user_new = User.empty();
            logger.error("Exception: updateUser UserServiceImpl");
        } finally {
            return user_new;
        }
    }

    @Override
    public List<User> getUserByParentId(Long selfId) {
        User user = userRepository.findOne(selfId);
        return  (user== null || user.getUserType().equals(User.FORTH_USER)) ? new ArrayList<>():
                user.getUserType().equals(User.TOP_USER) ? userRepository.findByUserType(User.SECOND_USER) :
                userRepository.findByParentId(selfId);
    }

    @Override
    public User createUser(Long selfId, User user) {
        User user_new = null;
        try {
            User pUser = userRepository.findOne(selfId);
            // 默认属性处理
            user.setUserKind(pUser.getUserKind());
            user.setParentId(selfId);
            if (user.getUserType().equals(User.THIRD_USER) && pUser.getUserType().equals(User.SECOND_USER)) {
                user.setName(null == user.getName() ? "未填写团委" : user.getName());
            } else {
                user.setUserType(User.FORTH_USER);
                user.setName(null == user.getName() ? "未填写团支部" : user.getName());
                if (pUser.getParentId() != null) {
                    user.setPparentId(pUser.getParentId());
                }
            }
            user.setId(null);
            user.setSecretaryName(user.getSecretaryName() == null ? "未填写书记" : user.getSecretaryName());

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
        if(pUser.getUserType().equals(User.TOP_USER)){
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
        if (null == user || !user.getParentId().equals(selfId)) {
            return 1;
        }
        // 级联删除
        if(user.getUserType().equals(User.THIRD_USER)) {
            userRepository.deleteByParentId(userId);
        }
        userRepository.delete(userId);

        return 0;
    }

    public User findFirstByAccount(String account) {
        return userRepository.findFirstByAccount(account);
    }

    public MemberCount getAllMeberNumAndAccout4() {
        MemberCount memberCount = new MemberCount();
        //统计学校的团员数量
        List<User> school = userRepository.findByUserKindAndUserType(User.SCHOOL, User.FORTH_USER);
        memberCount.setAccount4_school(school.size());
        int memberNums = 0;
        for(User user : school) {
            if (user.getMemberNum() != null) {
                memberNums += user.getMemberNum();
            }
        }
        memberCount.setMember_school(memberNums);
        //统计区县的数量
        List<User> ditrict = userRepository.findByUserKindAndUserType(User.DISTRICT, User.FORTH_USER);
        memberCount.setAccount4_school(ditrict.size());
        memberNums = 0;
        for(User user : ditrict) {
            if (user.getMemberNum() != null) {
                memberNums += user.getMemberNum();
            }
        }
        memberCount.setMember_district(memberNums);
        //统计城市的数量
        List<User> city = userRepository.findByUserKindAndUserType(User.CITY, User.FORTH_USER);
        memberCount.setAccount4_school(city.size());
        memberNums = 0;
        for(User user : city) {
            if (user.getMemberNum() != null) {
                memberNums += user.getMemberNum();
            }
        }
        memberCount.setMember_city(memberNums);
        memberCount.addAll();
        return memberCount;
    }
}

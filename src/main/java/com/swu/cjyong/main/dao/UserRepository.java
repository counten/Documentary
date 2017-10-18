package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findThirdByNameAndPasswd(String name, String passwd);
}


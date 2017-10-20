package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByAccountAndPasswd(String name, String passwd);

    List<User> findByParentId(Long parentId);

    User findFirstByAccount(String name);
}


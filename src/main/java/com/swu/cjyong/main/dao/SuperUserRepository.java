package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserRepository extends JpaRepository<SuperUser,Long> {

    SuperUser findFirstByNameAndPasswd(String name,String passwd);
}

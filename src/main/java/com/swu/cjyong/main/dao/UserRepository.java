package com.swu.cjyong.main.dao;

import com.swu.cjyong.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByNameAndPasswd(String name, String passwd);

    List<User> findUserByParentId(Long parentId);

    @Modifying
    @Query(value="DELETE from activity WHERE parentId= ?1",nativeQuery=true)
    void deleteByParentId(@Param("pId") Long pId);
}

